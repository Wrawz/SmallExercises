package com.lucatto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MobilePhone {

    private String MobilePhoneNumber;
    private List<Contact> contacts;

    public MobilePhone(String mobilePhoneNumber) {
        MobilePhoneNumber = mobilePhoneNumber;
        this.contacts = new ArrayList<>();
    }

    public boolean addContact(Contact contact) {
        if (isAlreadyContact(contact)) {
            System.out.println("Number \"" +contact.getPhoneNumber() + "\" already exists.");
            return false;
        }
        return contacts.add(new Contact(contact.getName(), contact.getPhoneNumber()));
    }

    public boolean removeContact(Contact contact) {
        if (isAlreadyContact(contact)) {
//            for (Contact contact1 : contacts)
//                if (contact1.getName().equals(contact.getName()) && contact1.getPhoneNumber().equals(contact.getPhoneNumber()))
//                    contacts.remove(contact1);
            contacts.removeIf(contact1 -> contact1.getName().equals(contact.getName()) && contact1.getPhoneNumber().equals(contact.getPhoneNumber()));
            return true;
        }
        System.out.println("Contact's number (" + contact.getPhoneNumber() + ") not found.");
        return false;
    }

    private boolean isAlreadyContact(Contact contact) {
        Collections.sort(contacts);
        return (Collections.binarySearch(contacts, contact, null) >= 0);
    }

    public boolean editContact(Contact contact, Contact modifiedContact) {
        if (isAlreadyContact(contact))
            for (Contact contact1 : contacts)
                if (contact1.getName().equals(contact.getName()) && contact1.getPhoneNumber().equals(contact.getPhoneNumber())) {
                    contact1.setName(modifiedContact.getName());
                    contact1.setPhoneNumber(modifiedContact.getPhoneNumber());
                    return true;
                }
        return false;
    }

    public void printContacts() { ;
        for (int i = 0; i < contacts.size(); i++) {
            if (i == contacts.size() - 1) {
                System.out.println("Name: " + contacts.get(i).getName() + "; number: " + contacts.get(i).getPhoneNumber() + ".");
                return;
            }
            System.out.println("Name: " + contacts.get(i).getName() + "; number: " + contacts.get(i).getPhoneNumber() + ";");
        }
    }
}
