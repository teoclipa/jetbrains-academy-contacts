package contacts;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class PersonRecord extends Record {
    private String name;
    private String surname;
    private String birthDate;
    private String gender;

    public PersonRecord(String name, String surname) {
        super(true);
        this.name = name;
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
        super.setLastEditTime(LocalDateTime.now());
    }

    public void setSurname(String surname) {
        this.surname = surname;
        super.setLastEditTime(LocalDateTime.now());
    }

    public void setBirthDate(String birthDate) {
        if (isValidBirthDate(birthDate)) {
            this.birthDate = birthDate;
        } else {
            this.birthDate = "[no data]";
            System.out.println("Wrong birth date format!");
        }
        super.setLastEditTime(LocalDateTime.now());
    }

    public void setGender(String gender) {
        if (isValidGender(gender)) {
            this.gender = gender;
        } else {
            this.gender = "[no data]";
            System.out.println("Wrong gender format! Enter either M or F.");
        }
        super.setLastEditTime(LocalDateTime.now());
    }

    public static boolean isValidBirthDate(String birthDate) {
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        return pattern.matcher(birthDate).matches();
    }

    public static boolean isValidGender(String gender) {
        return "M".equalsIgnoreCase(gender) || "F".equalsIgnoreCase(gender);
    }

    @Override
    public String getFullName() {
        return name + " " + surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }
}
