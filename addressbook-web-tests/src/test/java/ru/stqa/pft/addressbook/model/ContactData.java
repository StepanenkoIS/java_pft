package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

  private int id = Integer.MAX_VALUE;
  private String firstName;
  private String lastName;
  private String address;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String group;


  public String getPhoneHome() {
    return homePhone;
  }
  public String getPhoneMobile() {
    return mobilePhone;
  }
  public String getPhoneWork() {
    return workPhone;
  }

  public String getGroup() {
    return group;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withPhoneMobile(String mobile) {
    this.mobilePhone = mobile;
    return this;
  }
  public ContactData withPhoneHome(String home) {
    this.homePhone = home;
    return this;
  }
  public ContactData withPhoneWork(String work) {
    this.workPhone = work;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData data = (ContactData) o;
    return id == data.id &&
            Objects.equals(firstName, data.firstName) &&
            Objects.equals(lastName, data.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address='" + address + '\'' +
            ", group='" + group + '\'' +
            '}';
  }
}
