package com.contactlist.examly.service;

import java.sql.*;
import java.util.*;

import com.contactlist.examly.model.Contact;
import com.contactlist.examly.utility.ConnectionManager;

//JDBC
public class ContactDao{

    private ConnectionManager connectionManager;

    public ContactDao(){
        connectionManager = new ConnectionManager();
    }

    private static final String INSERT_CONTACT="INSERT INTO ContactList"+" (contactId, contactName, contactNumber, contactImage) VALUES"+" (?, ?, ?, ?);";
    private static final String SELECT_CONTACT_ID="select contactId, contactName, contactNumber, contactImage from ContactList where contactId=?;";
    private static final String UPDATE_CONTACT="update ContactList set contactName=?, contactNumber=?, contactImage=? where contactId=?;";
    private static final String DELETE_CONTACT="delete from ContactList where contactId=?;";
    private static final String SELECT_ALL_CONTACTS = "select * from ContactList";

    //Add contact
    public void addContact(Contact contact) throws SQLException{
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(INSERT_CONTACT)){
        preparedStatement.setInt(1, contact.getContactId());
        preparedStatement.setString(2, contact.getContactName());
        preparedStatement.setInt(3, contact.getContactNumber());
        preparedStatement.setString(4, contact.getContactImage());
        preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //Update contact
    public void updateContact(Contact contact) throws SQLException{
        try(Connection connection = connectionManager.getConnection();
        PreparedStatement statement=connection.prepareStatement(UPDATE_CONTACT)){
            statement.setString(2, contact.getContactName());
            statement.setInt(3, contact.getContactNumber());
            statement.setString(4, contact.getContactImage());
            statement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //View contact by id
    public Contact viewContactById(int contactId) throws SQLException {
        Contact contact = null;
        try(Connection connection = connectionManager.getConnection();
        PreparedStatement statement=connection.prepareStatement(SELECT_CONTACT_ID)){
            statement.setInt(1, contactId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String contactName = resultSet.getString("contactName");
                int contactNumber = resultSet.getInt("contactNumber");
                String contactImage = resultSet.getString("contactImage");
                contact = new Contact(contactId, contactName, contactNumber, contactImage);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return contact;
    }

    //Delete contact by id
    public void deleteContact(int contactId) throws SQLException{
        try(Connection connection = connectionManager.getConnection();
        PreparedStatement statement=connection.prepareStatement(DELETE_CONTACT)){
            statement.setInt(1, contactId);
            statement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //View all contacts
    public List<Contact> viewContacts() throws NullPointerException {
        List < Contact > contacts = new ArrayList < > ();
        try(Connection connection = connectionManager.getConnection();
        PreparedStatement statement=connection.prepareStatement(SELECT_ALL_CONTACTS)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int contactId = resultSet.getInt("contactId");
                String contactName = resultSet.getString("contactName");
                int contactNumber = resultSet.getInt("contactNumber");
                String contactImage = resultSet.getString("contactImage");
                contacts.add(new Contact(contactId, contactName, contactNumber, contactImage));
            }
        }catch(Throwable e){
            System.out.println("We get the error: " +e);
            e.printStackTrace();
        }
        return contacts;
    }

}