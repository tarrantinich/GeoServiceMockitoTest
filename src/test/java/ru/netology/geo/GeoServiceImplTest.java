package ru.netology.geo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

public class GeoServiceImplTest {

    @ParameterizedTest
    @MethodSource("testSourse2")
    public void byIpTest(String ip, Location expected) {
        GeoService geoService1 = new GeoServiceImpl();
        Location result = geoService1.byIp(ip);
        assertEquals(expected.getCountry(), result.getCountry());
        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getStreet(), result.getStreet());
        assertEquals(expected.getBuiling(), result.getBuiling());
    }

    private static Stream<Arguments> testSourse2() {
        return Stream.of(
                Arguments.of("172.55.62.4", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.36.31.12", new Location("New York", Country.USA, null, 0))
        );
    }

    @Test
    public void byCoordinatesTest() {
        GeoService geoService = new GeoServiceImpl();
        double latitude = 86.6;
        double longitude = 55.3;
        Class<RuntimeException> expected = RuntimeException.class;

        assertThrows(expected,
                () -> geoService.byCoordinates(latitude, longitude));
    }
}
