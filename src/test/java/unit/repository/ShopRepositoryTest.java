package unit.repository;

import com.db.test.bean.GeoLocation;
import com.db.test.bean.Shop;
import com.db.test.bean.builder.GeoLocationBuilder;
import com.db.test.bean.builder.ShopBuilder;
import com.db.test.repository.ShopRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class ShopRepositoryTest {

    private ShopRepository shopRepository;

    @Before
    public void setUp() throws Exception {
        shopRepository = new ShopRepository();
    }

    @Test
    public void testSizeWhenNoShopExists() throws Exception {
        assertEquals(0, shopRepository.size());
    }

    @Test
    public void testAddShop() throws Exception {
        GeoLocation location1 = new GeoLocationBuilder().setLat(1.2D).setLng(1.3D).createGeoLocation();

        shopRepository.addShop(new ShopBuilder().setShopName("ABC").setAddress(null).setGeoLocation(location1).createShop());

        assertEquals(1, shopRepository.size());
    }

    @Test
    public void testGetShopWhenExists() throws Exception {

        GeoLocation location1 = new GeoLocationBuilder().setLat(1.2D).setLng(1.3D).createGeoLocation();
        GeoLocation location2 = new GeoLocationBuilder().setLat(1.1D).setLng(1.4D).createGeoLocation();

        Shop shop1 = new ShopBuilder().setShopName("ABC").setAddress(null).setGeoLocation(location1).createShop();
        Shop shop2 = new ShopBuilder().setShopName("XYZ").setAddress(null).setGeoLocation(location2).createShop();

        shopRepository.addShop(shop1);
        shopRepository.addShop(shop2);

        assertEquals(shop1, shopRepository.getShop(location1));
        assertEquals(shop2, shopRepository.getShop(location2));

    }

    @Test
    public void testGetShopNotExistsAtLocation() throws Exception {

        GeoLocation location1 = new GeoLocationBuilder().setLat(1.2D).setLng(1.3D).createGeoLocation();

        assertNull(shopRepository.getShop(location1));
    }

    @Test
    public void testGetAllLocation() throws Exception {

        GeoLocation location = new GeoLocationBuilder().setLat(1.2D).setLng(1.3D).createGeoLocation();

        shopRepository.addShop(new ShopBuilder()
                    .setShopName("")
                    .setAddress(null)
                    .setGeoLocation(location)
                    .createShop());

        assertEquals(location, shopRepository.getAllLocation().toArray(new GeoLocation[0])[0]);
        assertEquals(1, shopRepository.getAllLocation().size());

    }

}