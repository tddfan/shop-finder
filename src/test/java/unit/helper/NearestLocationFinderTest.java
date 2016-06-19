package unit.helper;

import com.db.test.bean.GeoLocation;
import com.db.test.bean.builder.GeoLocationBuilder;
import com.db.test.helper.NearestLocationFinder;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NearestLocationFinderTest {

    private NearestLocationFinder nearestLocationFinder;

    @Before
    public void setUp() throws Exception {
        nearestLocationFinder = new NearestLocationFinder();
    }


    @Test
    public void testFindNearestLocationFor(){
        GeoLocation location1 = new GeoLocationBuilder().setLat(1.2D).setLng(1.3D).createGeoLocation();
        GeoLocation location2 = new GeoLocationBuilder().setLat(2.2D).setLng(2.3D).createGeoLocation();
        GeoLocation location3 = new GeoLocationBuilder().setLat(3.2D).setLng(3.3D).createGeoLocation();

        Set<GeoLocation> locations = new HashSet<>();
        locations.addAll(asList(location1, location2, location3));

        GeoLocation result = nearestLocationFinder.search(
                new GeoLocationBuilder().setLat(1.0D).setLng(1.0D).createGeoLocation()
                , locations);
        assertEquals(location1, result);

        GeoLocation result2 = nearestLocationFinder.search(
                new GeoLocationBuilder().setLat(2.0D).setLng(2.0D).createGeoLocation()
                , locations);
        assertEquals(location2, result2);

    }

    @Test
    public void testFindNearestLocationWhenEmptyLocationsPassed(){
        Set<GeoLocation> locations = new HashSet<>();

        GeoLocation result = nearestLocationFinder.search(
                new GeoLocationBuilder().setLat(1.0D).setLng(1.0D).createGeoLocation()
                , locations);

        assertNull(result);
    }
}
