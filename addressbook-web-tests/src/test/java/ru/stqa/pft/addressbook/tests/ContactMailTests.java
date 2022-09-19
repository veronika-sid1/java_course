package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMailTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Olga").withLastname("Smolova")
              .withMail("olgasm@mail.com").withGroup("test1").withHomePhone("79821234323").withAddress("улица Королева 7, дом 3, квартира 7"));
    }
  }

  @Test
  public void testContactMail() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllMails(), equalTo(mergeMails(contactInfoFromEditForm)));
  }

  private String mergeMails(ContactData contact) {
    return Arrays.asList(contact.getMail(), contact.getMail2(), contact.getMail3())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactMailTests::cleaned)
            .collect((Collectors.joining("\n")));
  }

  public static String cleaned(String mail) {
    return mail.replaceAll("\\s+", " ");
  }

}
