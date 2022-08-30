package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String phone;
  private final String mail;
  private String group;

  public ContactData(String firstname, String lastname, String phone, String mail, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.phone = phone;
    this.mail = mail;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getPhone() {
    return phone;
  }

  public String getMail() {
    return mail;
  }

  public String getGroup() {
    return group;
  }
}
