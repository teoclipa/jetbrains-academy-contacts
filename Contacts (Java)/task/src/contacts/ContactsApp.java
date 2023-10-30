package contacts;

import java.util.List;
import java.util.Scanner;

public class ContactsApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        PhoneBook phoneBook = new PhoneBook();

        while (true) {
            System.out.println("Enter action (add, remove, edit, count, info, exit):");
            String action = scanner.nextLine().toLowerCase();

            switch (action) {
                case "add":
                    addRecord(phoneBook);
                    break;
                case "remove":
                    removeRecord(phoneBook);
                    break;
                case "edit":
                    editRecord(phoneBook);
                    break;
                case "count":
                    System.out.println("The Phone Book has " + phoneBook.countRecords() + " records.");
                    break;
                case "info":
                    showInfo(phoneBook);
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Unknown action!");
            }
            System.out.println();
        }
    }

    private static void addRecord(PhoneBook phoneBook) {
        System.out.println("Enter the type (person, organization):");
        String type = scanner.nextLine();

        switch (type.toLowerCase()) {
            case "person":
                addPerson(phoneBook);
                break;
            case "organization":
                addOrganization(phoneBook);
                break;
            default:
                System.out.println("Unknown type!");
        }
    }

    private static void showInfo(PhoneBook phoneBook) {
        if (phoneBook.countRecords() == 0) {
            System.out.println("No records to display!");
            return;
        }

        phoneBook.listRecords();
        System.out.println("Enter index to show info:");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        Record record = phoneBook.getRecord(index);
        if (record == null) {
            System.out.println("Invalid index!");
            return;
        }

        if (record.isPerson()) {
            displayPersonInfo((PersonRecord) record);
        } else {
            displayOrganizationInfo((OrganizationRecord) record);
        }
    }

    private static void displayPersonInfo(PersonRecord person) {
        System.out.println("Name: " + person.getFullName().split(" ")[0]);
        System.out.println("Surname: " + person.getFullName().split(" ")[1]);
        System.out.println("Birth date: " + (person.getBirthDate().isEmpty() ? "[no data]" : person.getBirthDate()));
        System.out.println("Gender: " + (person.getGender().isEmpty() ? "[no data]" : person.getGender()));
        System.out.println("Number: " + person.getPhoneNumber());
        System.out.println("Time created: " + person.getCreationTime());
        System.out.println("Time last edit: " + person.getLastEditTime());
    }

    private static void displayOrganizationInfo(OrganizationRecord organization) {
        System.out.println("Organization name: " + organization.getFullName());
        System.out.println("Address: " + organization.getAddress());
        System.out.println("Number: " + organization.getPhoneNumber());
        System.out.println("Time created: " + organization.getCreationTime());
        System.out.println("Time last edit: " + organization.getLastEditTime());
    }
    private static void addPerson(PhoneBook phoneBook) {
        System.out.println("Enter the name:");
        String name = scanner.nextLine();
        System.out.println("Enter the surname:");
        String surname = scanner.nextLine();
        PersonRecord personRecord = new PersonRecord(name, surname);


        System.out.println("Enter the birth date:");
        String birthDate = scanner.nextLine();
        personRecord.setBirthDate(birthDate);

        System.out.println("Enter the gender (M, F):");
        String gender = scanner.nextLine();
        personRecord.setGender(gender);

        System.out.println("Enter the number:");
        String number = scanner.nextLine();
        personRecord.setPhoneNumber(number);



        phoneBook.addRecord(personRecord);
        System.out.println("The record added.");
    }

    private static void addOrganization(PhoneBook phoneBook) {
        System.out.println("Enter the organization name:");
        String name = scanner.nextLine();
        System.out.println("Enter the address:");
        String address = scanner.nextLine();
        System.out.println("Enter the number:");
        String number = scanner.nextLine();

        OrganizationRecord orgRecord = new OrganizationRecord(name, address);
        orgRecord.setPhoneNumber(number);
        phoneBook.addRecord(orgRecord);
        System.out.println("The record added.");
    }

    private static void removeRecord(PhoneBook phoneBook) {
        if (phoneBook.countRecords() == 0) {
            System.out.println("No records to remove!");
            return;
        }

        phoneBook.listRecords();
        System.out.println("Select a record:");
        int index = Integer.parseInt(ContactsApp.scanner.nextLine()) - 1;
        phoneBook.removeRecord(index);
        System.out.println("The record removed!");
    }

    private static void editRecord(PhoneBook phoneBook) {
        List<Record> records = phoneBook.getRecords();
        if (records.isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }

        // Display all records
        for (int i = 0; i < records.size(); i++) {
            System.out.println((i + 1) + ". " + records.get(i).getFullName());
        }

        System.out.println("Select a record:");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index >= records.size()) {
            System.out.println("No such record");
            return;
        }

        Record selectedRecord = records.get(index);

        if (selectedRecord.isPerson()) {
            editPerson((PersonRecord) selectedRecord);
        } else {
            editOrganization((OrganizationRecord) selectedRecord);
        }
        System.out.println("The record updated!");
    }

    private static void editPerson(PersonRecord person) {
        System.out.println("Select a field (name, surname, birth, gender, number):");

        String field = scanner.nextLine().toLowerCase();
        switch (field) {
            case "name":
                System.out.println("Enter name:");
                person.setName(scanner.nextLine());
                break;
            case "surname":
                System.out.println("Enter surname:");
                person.setSurname(scanner.nextLine());
                break;
            case "birth":
                System.out.println("Enter the birth date:");
                String birthDate = scanner.nextLine();
                person.setBirthDate(birthDate);
                break;
            case "gender":
                System.out.println("Enter the gender (M, F):");
                String gender = scanner.nextLine();
                person.setGender(gender);
                break;
            case "number":
                System.out.println("Enter number:");
                person.setPhoneNumber(scanner.nextLine());
                break;
            default:
                System.out.println("Unknown field");
        }
    }

    private static void editOrganization(OrganizationRecord organization) {
        System.out.println("Select a field (address, number):");

        String field = scanner.nextLine().toLowerCase();
        switch (field) {
            case "address":
                System.out.println("Enter address:");
                organization.setAddress(scanner.nextLine());
                break;
            case "number":
                System.out.println("Enter number:");
                organization.setPhoneNumber(scanner.nextLine());
                break;
            default:
                System.out.println("Unknown field");
        }
    }
}
