package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

public class LocalizationServiceImplTest {

    @Test
    public void localeTest() {
        LocalizationService localizationService = new LocalizationServiceImpl();

        Country country1 = RUSSIA;
        Country country2 = USA;

        String result1 = localizationService.locale(country1);
        String expected1 = "Добро пожаловать";
        assertEquals(expected1, result1);

        String result2 = localizationService.locale(country2);
        String expected2 = "Welcome";
        assertEquals(expected2, result2);
    }
}
