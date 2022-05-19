package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MessageSenderTest {
    @ParameterizedTest
    @MethodSource("testSourse")
    public void sendTest(Location location, String ip, String expected) {
        GeoService geoService = Mockito.mock(GeoService.class);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        Mockito.when(geoService.byIp(ip))
                .thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(location.getCountry()))
                .thenReturn(expected);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        String result = messageSender.send(headers);
        Assertions.assertEquals(result, expected);
    }

    private static Stream<Arguments> testSourse() {
        return Stream.of(
                Arguments.of(new Location("Moscow", Country.RUSSIA, null, 0), "172.123.12.19", "Добро пожаловать"),
                Arguments.of(new Location("New York", Country.USA, null, 0), "96.36.31.12", "Welcome")
        );
    }
}
