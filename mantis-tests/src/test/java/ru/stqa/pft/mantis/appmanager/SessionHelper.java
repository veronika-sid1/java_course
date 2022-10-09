package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase{

  public SessionHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("span.submit-button > button > span"));

  }

  public void manageUsers() throws InterruptedException {
    click(By.cssSelector("div.navbar-container > button"));
    Thread.sleep(1000);
    click(By.xpath("//*[@id=\"sidebar\"]/ul/li[6]/a"));
    click(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/ul/li[2]/a"));
  }

  public void chooseUser(String name) {
    click(By.linkText(name));
  }

  public void resetPassword() {
    click(By.cssSelector("input[value='Reset Password']"));
  }
}

