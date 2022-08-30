package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationTests(){
    app.getNavigationHelper().gotoContactCreationPage();
    app.getContactHelper().fillContactInformation(new ContactData("Olga", "Smirnova", "89111234567", "olgasm@mail.com", "test3"), true);
    app.getContactHelper().submitContactCreation();
    app.returnToHomePage();
  }

}
