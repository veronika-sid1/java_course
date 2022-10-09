package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class PasswordChangeTest extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testPasswordChange() throws InterruptedException, MessagingException, IOException {
    app.changePassHelper().start(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.changePassHelper().manageUsers();
    UserData user = app.db().getNonAdminUsers().stream().iterator().next();
    app.changePassHelper().chooseUser(user.getUsername());
    app.changePassHelper().resetPassword();

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());

    long now = System.currentTimeMillis();
    String newpassword = String.format("password%s", now);
    app.changePassHelper().finish(confirmationLink, newpassword);
    assertTrue(app.newSession().login(user.getUsername(), newpassword));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}

