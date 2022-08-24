package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationTests() throws Exception {
    app.gotoContactCreationPage();
    app.fillContactInformation(new ContactData("Olga", "Smirnova", "89111234567", "olgasm@mail.com"));
    app.submitContactCreation();
    app.returnToHomePage();
  }

}
