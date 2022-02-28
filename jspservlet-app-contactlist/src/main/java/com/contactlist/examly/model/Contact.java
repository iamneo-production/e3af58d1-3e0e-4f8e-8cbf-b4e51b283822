package com.contactlist.examly.model;

public class Contact{
    private int contactId;
    private String contactName;
    private int contactNumber;
    private String contactImage;

    Contact() {

    }

    public Contact(int contactId, String contactName, int contactNumber, String contactImage){
        this.contactId=contactId;
        this.contactName=contactName;
        this.contactNumber=contactNumber;
        this.contactImage=contactImage;
    }

    public Contact(String contactName, int contactNumber, String contactImage){
        super();
        this.contactName=contactName;
        this.contactNumber=contactNumber;
        this.contactImage=contactImage;
    }

    public int getContactId(){
        return contactId;
    }

    public void setContactId(int contactId){
        this.contactId=contactId;
    }

    public String getContactName(){
        return contactName;
    }

    public void setContactName(String contactName){
        this.contactName=contactName;
    }

    public int getContactNumber(){
        return contactNumber;
    }

    public void setContactNumber(int contactNumber){
        this.contactNumber=contactNumber;
    }

    public String getContactImage(){
        return contactImage;
    }
    
    public void setContactImage(String contactImage){
        this.contactImage=contactImage;
    }

}