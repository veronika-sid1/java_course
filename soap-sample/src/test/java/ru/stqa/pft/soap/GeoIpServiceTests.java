package ru.stqa.pft.soap;

import lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("178.66.209.105");
    assertEquals(geoIp, "RU");
  }
}
