package contacts;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public abstract class Record {
    protected String phoneNumber;
    protected LocalDateTime creationTime;
    protected LocalDateTime lastEditTime;
    protected boolean isPerson;

    public Record(boolean isPerson) {
        this.isPerson = isPerson;
        this.creationTime = LocalDateTime.now();
        this.lastEditTime = LocalDateTime.now();
    }

    public abstract String getFullName();

    public boolean isPerson() {
        return isPerson;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(LocalDateTime lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "[no number]";
            System.out.println("Wrong number format!");
        }
        this.lastEditTime = LocalDateTime.now();
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\+?\\w*[ -]?\\(?\\w{2,}\\)?([ -]\\w{2,})*$|^\\w+$");
        return pattern.matcher(phoneNumber).find();
    }
}
