package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

  private int id;
  private final String firstname;
  private final String lastname;
  private final String phone;
  private final String mail;
  private String group;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }

  public ContactData(String firstname, String lastname, String phone, String mail, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
    this.phone = phone;
    this.mail = mail;
    this.group = group;
  }

  public ContactData(int id, String firstname, String lastname, String phone, String mail, String group) {
    this.id = id;
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

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

}
