package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactInformation(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("mobile"),contactData.getPhone());
    type(By.name("email"),contactData.getMail());
  }

  public void submitContactDeletion() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void chooseContact() {
    click(By.xpath("//input[@type='checkbox']"));
  }
}
