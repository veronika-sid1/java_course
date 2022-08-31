package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Olga", "Smirnova", "89111234567", "olgasm@mail.com", "test1"));
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactInformation(new ContactData("Olga", "Smolova", "89111234567", "olgasm@mail.com", null), false);
    app.getContactHelper().updateContactInformation();
    app.getContactHelper().returnToHomePage();
  }
}
