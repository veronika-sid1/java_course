package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

public class TestBase {

  public boolean isIssueOpen(int issueId) {
    String json = RestAssured.get("https://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    Integer state = parsed.getAsJsonObject().get("issues")
            .getAsJsonArray().get(0).getAsJsonObject().get("state").getAsInt();
    if (state.equals(0)) {
      return true;
    }
      return false;
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
