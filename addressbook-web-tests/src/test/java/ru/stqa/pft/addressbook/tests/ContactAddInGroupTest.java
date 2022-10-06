package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactAddInGroupTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
    if (app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("Olga").withLastname("Smolova").withMail("olgasm@mail.com").withHomePhone("79821234323").inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testContactAddInGroup() {
    app.goTo().homePage();
    app.contact().chooseContactsToGroup("[all]");
    ContactData contact = app.db().contacts().stream().iterator().next();

    if (contact.getGroups().equals(app.db().groups())) {
      app.goTo().groupPage();
      GroupData newGroup = new GroupData().withName("AddedGroup").withHeader("SomeHeader").withFooter("SomeFooter");
      app.group().create(newGroup);
      app.goTo().homePage();
    }

    app.contact().chooseById(contact.getId());
    GroupData chooseGroup = app.group().chooseGroupToAdd(contact, app.db().groups());
    app.contact().chooseNeededGroup(chooseGroup);
    app.contact().submitAdditionToGroup();
    assertThat(app.db().getContactById(contact.getId()).getGroups().contains(chooseGroup), equalTo(true));
    VerifyGroupListInUi();
  }
}
