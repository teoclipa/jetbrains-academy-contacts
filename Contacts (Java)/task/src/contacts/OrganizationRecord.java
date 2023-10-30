package contacts;

import java.time.LocalDateTime;

public class OrganizationRecord extends Record {
    private final String organizationName;
    private String address;

    public OrganizationRecord(String organizationName, String address) {
        super(false);
        this.organizationName = organizationName;
        this.address = address;
    }

    public void setAddress(String address) {
        this.address = address;
        super.setLastEditTime(LocalDateTime.now());
    }

    @Override
    public String getFullName() {
        return organizationName;
    }

    public String getAddress() {
        return address;
    }
}
