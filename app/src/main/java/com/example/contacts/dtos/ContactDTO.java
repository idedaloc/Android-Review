package com.example.contacts.dtos;

public class ContactDTO {
    private Integer contactId;
    private String contactName;
    private Long phone;

    public ContactDTO(Integer contactId, String contactName, Long phone) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.phone = phone;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
