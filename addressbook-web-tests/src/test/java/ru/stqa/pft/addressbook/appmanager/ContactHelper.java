package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

import static org.testng.Assert.assertFalse;

public class ContactHelper extends HelperBase {

  private ApplicationManager manager;

  public ContactHelper(ApplicationManager manager) {
    super(manager.wd);
    this.manager = manager;
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fill(ContactData contact, boolean creation) {
    type(By.name("firstname"), contact.getFirstname());
    type(By.name("lastname"), contact.getLastname());;
    type(By.name("email"), contact.getMail());
    type(By.name("email2"), contact.getMail2());
    type(By.name("email3"), contact.getMail3());
    type(By.name("home"), contact.getHomePhone());
    type(By.name("mobile"), contact.getMobilePhone());
    type(By.name("work"), contact.getWorkPhone());
    //attach(By.name("photo"), contact.getPhoto());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroup());
    } else {
      assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void create(ContactData contact) {
    gotoContactCreationPage();
    fill(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    chooseById(contact.getId());
    chooseDelete();
    manager.acceptAlert();
    contactCache = null;
    manager.goTo().homePage();
  }

  public void modify(ContactData contact) {
    chooseModifyById(contact.getId());
    fill(contact, false);
    update();
    contactCache = null;
    returnToHomePage();
  }

  public void chooseDelete() {
    click(By.xpath("//input[@value='Delete']"));
  }


  public void chooseById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void chooseModifyById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
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

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
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
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = String.valueOf(cells.get(2).getText());
      String lastname = String.valueOf(cells.get(1).getText());
      String allPhones = cells.get(5).getText();
      String address = cells.get(3).getText();
      String allMails = cells.get(4).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAllPhones(allPhones)
              .withAddress(address).withAllMails(allMails));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    chooseModifyById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String mail = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address).withMail(mail)
            .withEmail2(email2).withEmail3(email3);
  }
}


