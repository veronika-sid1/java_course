package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String phone;
  private final String mail;

  public ContactData(String firstname, String lastname, String phone, String mail) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.phone = phone;
    this.mail = mail;
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
}
