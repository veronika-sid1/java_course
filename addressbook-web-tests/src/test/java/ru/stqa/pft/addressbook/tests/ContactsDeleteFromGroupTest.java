package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsDeleteFromGroupTest extends TestBase {

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
  public void testContactDeleteFromGroup1() {
    app.goTo().homePage();
    app.contact().chooseContactsToGroup("[all]");
    ContactData contact = app.db().contacts().stream().iterator().next();
    GroupData group = app.db().groups().stream().iterator().next();

    if (contact.getGroups().size() == 0 || ! contact.getGroups().contains(group)) {
      app.contact().chooseById(contact.getId());
      app.contact().chooseNeededGroup(group);
      app.contact().submitAdditionToGroup();
      app.goTo().homePage();
    }

    assertThat(app.db().getContactById(contact.getId()).getGroups().contains(group), equalTo(true));

    app.contact().selectGroupForRemoval(group);
    app.contact().chooseById(contact.getId());
    app.contact().removeFromGroup();

    assertThat(app.db().getContactById(contact.getId()).getGroups().contains(group), equalTo(false));
    VerifyGroupListInUi();
  }
}
