package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private final List<Record> records;

    public PhoneBook() {
        this.records = new ArrayList<>();
    }

    public void addRecord(Record record) {
        records.add(record);
    }

    public void removeRecord(int index) {
        if (index >= 0 && index < records.size()) {
            records.remove(index);
        }
    }

    public Record getRecord(int index) {
        if (index >= 0 && index < records.size()) {
            return records.get(index);
        }
        return null;
    }

    public List<Record> getRecords() {
        return records;
    }

    public int countRecords() {
        return records.size();
    }

    public void listRecords() {
        for (int i = 0; i < records.size(); i++) {
            System.out.println((i + 1) + ". " + records.get(i).getFullName());
        }
    }
}
