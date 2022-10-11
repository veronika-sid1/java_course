package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static java.math.BigInteger.valueOf;
import static org.testng.Assert.assertEquals;
import static ru.stqa.pft.mantis.appmanager.SoapHelper.getMantisConnect;


public class SoapTests extends TestBase{

  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects){
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test Issue")
            .withDescription("Test issue description").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }

  @Test
  public void testCheckBugReportStatus() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    MantisConnectPortType mc = getMantisConnect();
    IssueData[] issues = mc.mc_project_get_issues(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"),
            valueOf(projects.iterator().next().getId()), valueOf(1-10), valueOf(-1));
    for (IssueData issue : issues) {
      skipIfNotFixed(issue.getId());
    }
  }
}






