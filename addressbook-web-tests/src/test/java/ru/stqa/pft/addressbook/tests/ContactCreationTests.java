package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationTests(){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("Olga", "Smirnova", "89111234567", "olgasm@mail.com", "test1"));
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }
}
