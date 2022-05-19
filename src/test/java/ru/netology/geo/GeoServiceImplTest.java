package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeoServiceImplTest {

    @Test
    public void byIpTest() {
        GeoService geoService1 = new GeoServiceImpl();

        String ip1 = "172.55.62.4";
        Location result1 = geoService1.byIp(ip1);
        Location expected1 = new Location("Moscow", Country.RUSSIA, null, 0);
        assertEquals(expected1.getCountry(), result1.getCountry());
        assertEquals(expected1.getCity(), result1.getCity());
        assertEquals(expected1.getStreet(), result1.getStreet());
        assertEquals(expected1.getBuiling(), result1.getBuiling());

        GeoService geoService2 = new GeoServiceImpl();
        String ip2 = "96.36.31.12";
        Location result2 = geoService2.byIp(ip2);
        Location expected2 = new Location("New York", Country.USA, null, 0);
        assertEquals(expected2.getCountry(), result2.getCountry());
        assertEquals(expected2.getCity(), result2.getCity());
        assertEquals(expected2.getStreet(), result2.getStreet());
        assertEquals(expected2.getBuiling(), result2.getBuiling());
    }

    @Test
    public void byCoordinatesTest(){
        GeoService geoService = new GeoServiceImpl();
        double latitude = 86.6;
        double longitude = 55.3;
        Class<RuntimeException> expected = RuntimeException.class;

        assertThrows(expected,
                () -> geoService.byCoordinates(latitude,longitude));
    }
}
