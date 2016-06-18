package unit.service;

import com.db.test.bean.Address;
import com.db.test.bean.GeoLocation;
import com.db.test.config.App;
import com.db.test.service.GeoLocationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class GeoLocationServiceTest {

    @Autowired
    GeoLocationService geoLocationService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testFindLocation() {

        Address address = new Address("280","EC2M 4AA");

        GeoLocation location = geoLocationService.findLocation(address);

        assertNotNull(location);
    }

}