import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceTest {

    @Test
    void testByIpLocalhost() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp(GeoServiceImpl.LOCALHOST);

        assertNull(location.getCity());
        assertNull(location.getCountry());
        assertNull(location.getStreet());
        assertEquals(0, location.getBuilding());
    }

    @Test
    void testByIpMoscow() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp(GeoServiceImpl.MOSCOW_IP);

        assertEquals("Moscow", location.getCity());
        assertEquals(Country.RUSSIA, location.getCountry());
        assertEquals("Lenina", location.getStreet());
        assertEquals(15, location.getBuilding());
    }

    @Test
    void testByIpNewYork() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);

        assertEquals("New York", location.getCity());
        assertEquals(Country.USA, location.getCountry());
        assertEquals("10th Avenue", location.getStreet());
        assertEquals(32, location.getBuilding());
    }

    @Test
    void testByIpRussianSegment() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp("172.16.254.1");

        assertEquals("Moscow", location.getCity());
        assertEquals(Country.RUSSIA, location.getCountry());
        assertNull(location.getStreet());
        assertEquals(0, location.getBuilding());
    }

    @Test
    void testByIpAmericanSegment() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp("96.44.183.150");

        assertEquals("New York", location.getCity());
        assertEquals(Country.USA, location.getCountry());
        assertNull(location.getStreet());
        assertEquals(0, location.getBuilding());
    }

    @Test
    void testByIpUnknown() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp("8.8.8.8");

        assertNull(location);
    }
}