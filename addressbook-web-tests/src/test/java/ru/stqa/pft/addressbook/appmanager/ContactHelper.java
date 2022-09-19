package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  private ApplicationManager manager;

  public ContactHelper(ApplicationManager manager) {
    super(manager.wd);
    this.manager = manager;
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fill(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("mobile"), contactData.getPhone());
    type(By.name("email"), contactData.getMail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void chooseDelete() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void delete(ContactData contact) {
    chooseById(contact.getId());
    chooseDelete();
    manager.acceptAlert();
    contactCache = null;
    manager.goTo().homePage();
  }

  public void chooseById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void chooseModifyById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void modify(ContactData contact) {
    chooseModifyById(contact.getId());
    fill(contact, false);
    update();
    contactCache = null;
    returnToHomePage();
  }

  public void update() {
    click(By.name("update"));
  }

  public void gotoContactCreationPage() {
    click(By.linkText("add new"));
  }

  public void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  public void create(ContactData contact) {
    gotoContactCreationPage();
    fill(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.xpath("//input[@type='checkbox']")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstname = String.valueOf(cells.get(2).getText());
      String lastname = String.valueOf(cells.get(1).getText());
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact);
    }
    return contacts;
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstname = String.valueOf(cells.get(2).getText());
      String lastname = String.valueOf(cells.get(1).getText());
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return new Contacts(contactCache);
  }

}


