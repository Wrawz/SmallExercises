package com.lucatto;

public class Main {

    public static void main(String[] args) {
        MobilePhone mobilePhone = new MobilePhone("12341234");
        Contact contact1 = new Contact("Peter", "23452345");
        Contact contact2 = new Contact("John", "34563456");
        Contact contact3 = new Contact("Bob", "45674567");
        mobilePhone.addContact(contact1);
        mobilePhone.addContact(contact2);
        mobilePhone.addContact(contact3);
        mobilePhone.addContact(new Contact("David", "23452345"));
        System.out.println();
        mobilePhone.printContacts();

        System.out.println("\n-----------------");
        mobilePhone.editContact(contact2, new Contact("John", "01019292"));
        mobilePhone.printContacts();

        System.out.println("\n-----------------");
        mobilePhone.editContact(contact1, new Contact("Eric", "56564747"));
        mobilePhone.printContacts();

        System.out.println("\n-----------------");
        mobilePhone.removeContact(new Contact("John", "34563456"));
        mobilePhone.printContacts();

        System.out.println("\n-----------------");
        mobilePhone.removeContact(new Contact("John", "01019292"));
        mobilePhone.printContacts();
    }

}
