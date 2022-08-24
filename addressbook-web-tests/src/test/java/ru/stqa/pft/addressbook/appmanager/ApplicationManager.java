package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {

  WebDriver wd;
  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;

  public void init() {
    wd = new ChromeDriver();
    wd.get("http://localhost/addressbook/");
    contactHelper = new ContactHelper(wd);
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  public void stop() {
    logout();
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  public void acceptAlert() {
    wd.switchTo().alert().accept();
  }

}
