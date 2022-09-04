package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Olga", "Smirnova", "89111234567", "olgasm@mail.com", "test1"));
    }
    app.getContactHelper().chooseContact();
    app.getContactHelper().submitContactDeletion();
    app.acceptAlert();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }

}
