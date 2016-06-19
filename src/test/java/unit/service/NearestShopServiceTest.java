package unit.service;


import com.db.test.bean.GeoLocation;
import com.db.test.bean.Shop;
import com.db.test.bean.builder.AddressBuilder;
import com.db.test.bean.builder.GeoLocationBuilder;
import com.db.test.bean.builder.ShopBuilder;
import com.db.test.helper.NearestLocationFinder;
import com.db.test.repository.ShopRepository;
import com.db.test.service.NearestShopService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NearestShopServiceTest {

    @Mock private ShopRepository shopRepository;
    @Mock private NearestLocationFinder locationFinder;

    private NearestShopService nearestShopService;

    @Before
    public void setUp() throws Exception {
        nearestShopService = new NearestShopService(shopRepository, locationFinder);
    }

    @Test
    public void testFindNearestShop() {
        GeoLocation searchLocation = new GeoLocationBuilder().setLat(1D).setLng(1D).createGeoLocation();
        GeoLocation location1 = new GeoLocationBuilder().setLat(1.2D).setLng(1.3D).createGeoLocation();

        Set<GeoLocation> locations = new HashSet<>();
        locations.add(location1);
        Shop shop = new ShopBuilder()
                    .setShopName("ABC")
                    .setAddress(new AddressBuilder().setNumber("1").setPostcode("E16").createAddress())
                    .setGeoLocation(location1).createShop();

        when(shopRepository.getAllLocation()).thenReturn(locations);
        when(locationFinder.search(searchLocation, locations)).thenReturn(location1);
        when(shopRepository.getShop(location1)).thenReturn(shop);

        Shop nearestShop = nearestShopService.find(new GeoLocationBuilder().setLat(1D).setLng(1D).createGeoLocation());

        assertEquals(shop, nearestShop);

    }



}
