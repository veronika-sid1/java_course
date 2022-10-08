package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[type='submit']"));
  }

  public void finish(String confirmationLink, String name, String password) {
    wd.get(confirmationLink);
    type(By.name("realname"), name);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("span.submit-button > button > span"));
  }
}
