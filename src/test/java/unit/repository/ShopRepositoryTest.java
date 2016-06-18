package unit.repository;

import com.db.test.bean.GeoLocation;
import com.db.test.bean.Shop;
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
        GeoLocation location1 = new GeoLocation(1.2D, 1.3D);
        shopRepository.addShop(new Shop("ABC", null, location1));

        assertEquals(1, shopRepository.size());
    }

    @Test
    public void testGetShopWhenExists() throws Exception {
        GeoLocation location1 = new GeoLocation(1.2D, 1.3D);
        GeoLocation location2 = new GeoLocation(1.1D, 1.4D);
        Shop shop1 = new Shop("ABC", null, location1);
        Shop shop2 = new Shop("XYZ", null, location2);

        shopRepository.addShop(shop1);
        shopRepository.addShop(shop2);

        assertEquals(shop1, shopRepository.getShop(location1));
        assertEquals(shop2, shopRepository.getShop(location2));

    }

    @Test
    public void testGetShopNotExistsAtLocation() throws Exception {
        GeoLocation location1 = new GeoLocation(1.2D, 1.3D);
        assertNull(shopRepository.getShop(location1));
    }

    @Test
    public void testGetAllLocation() throws Exception {

        GeoLocation location = new GeoLocation(1.2D, 1.3D);

        shopRepository.addShop(new Shop("", null, location));

        assertEquals(location, shopRepository.getAllLocation().toArray(new GeoLocation[0])[0]);
        assertEquals(1, shopRepository.getAllLocation().size());

    }

}