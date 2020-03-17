package com.company;

import java.util.ArrayList;

public class MobilePhone {
    private String myNumber;
    private ArrayList<Contact> contacts;

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.contacts = new ArrayList<>();
    }

    public String getMyNumber() {
        return myNumber;
    }

    public void setMyNumber(String myNumber) {
        this.myNumber = myNumber;
    }

    public void printContacts() {
        for (Contact contact : contacts) {
            System.out.println("Contact Name: " + contact.getName() + "\nPhone Number: " +
                    contact.getPhoneNumber() + "\n");
        }
    }

    public boolean addNewContact(Contact contact) {
        if(findContact(contact.getName()) >= 0) {
            System.out.println("Contact is already on file");
            return false;
        }
        contacts.add(contact);
        return true;
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        int position = findContact(oldContact.getName());
        if (position >= 0) {
            contacts.set(position, newContact);
            return true;
        }
        System.out.println("Contact is not in your contacts");
        return false;
    }

    public void removeContact(Contact contact) {
        int position = findContact(contact.getName());
        if(position >= 0) {
            contacts.remove(position);
            System.out.println("Contact Removed");
        } else {
            System.out.println("Contact not found");
        }

    }

    private int findContact(Contact contact) {
        return this.contacts.indexOf(contact);
    }

    private int findContact(String contactName) {
        for (int i = 0; i < contacts.size(); i ++) {
            Contact contact = this.contacts.get(i);
            if(contact.getName().equals(contactName)) {
                return i;
            }
        }
        return -1;
    }

    public void inContacts(Contact contact) {
        inContacts(contact.getName());
    }

    public void inContacts(String contactName) {
        if(findContact(contactName) >= 0) {
            Contact contact = getContact(contactName);
            System.out.println("Contact " + contact.getName() + " is in your contact list, phone number: " +
                    contact.getPhoneNumber());
        } else {
            System.out.println("Contact not found");
        }

    }

    public Contact getContact(String contactName) {
        int position = findContact(contactName);
        if(position >= 0) {
            return contacts.get(position);
        }
        return null;
    }



}
