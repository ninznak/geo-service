import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;


import static org.mockito.Mockito.*;

class MokitoTest {

    @Test
    void testSendRussianMessageForRussianIp() {

        GeoService geoService = mock(GeoService.class);
        LocalizationService localizationService = mock(LocalizationService.class);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");

        Location russianLocation = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        when(geoService.byIp("172.0.32.11")).thenReturn(russianLocation);
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        String result = messageSender.send(headers);
    }

    @Test
    void testSendEnglishMessageForAmericanIp() {

        GeoService geoService = mock(GeoService.class);
        LocalizationService localizationService = mock(LocalizationService.class);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        Location americanLocation = new Location("New York", Country.USA, "10th Avenue", 32);
        when(geoService.byIp("96.44.183.149")).thenReturn(americanLocation);
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        String result = messageSender.send(headers);
    }
}
