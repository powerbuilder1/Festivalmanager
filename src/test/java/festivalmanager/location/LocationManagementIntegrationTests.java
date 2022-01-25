package festivalmanager.location;

import static org.assertj.core.api.Assertions.*;

import static org.salespointframework.core.Currencies.*;


import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Streamable;

import festivalmanager.AbstractIntegrationTests;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationManagementIntegrationTests extends AbstractIntegrationTests {

    @Autowired
    private LocationManagement locationManagement;

    /**
     * Make sure the test location(s) don't exist before the test
     * @param name
     */
    void deleteLocation(String name) {
        Streamable<Location> locations = locationManagement.findAllByName(name);
        for(Location location : locations)
        {
            locationManagement.deleteById(location.getId());
        }
    }

    @Test
    void createLocation() {
        deleteLocation("TestLocation");
        LocationForm form = new LocationForm(12, 1, "TestLocation", 500, 200);
        locationManagement.createLocation(form);
        assertThat(locationManagement.findAllByName(form.getName())).hasSize(1);
        deleteLocation("TestLocation");
    }

    @Test
    void findsAllLocations() {
        Streamable<Location> locations = locationManagement.findAllLocations();
        assertThat(locations).hasSizeGreaterThan(-1);
    }

    @Test
    void checkLocationContent() {
        String name = "somerandomnameforthistest";
        deleteLocation(name);
        // add test location
        locationManagement.createLocation(name, 200, 10, Money.of(500, EURO));

        // find location
        Streamable<Location> locations = locationManagement.findAllByName(name);
        assertThat(locations).hasSize(1);

        // check its contents
        Location location = locations.toList().get(0);
        assertThat(location.getName()).isEqualTo(name);
        assertThat(location.getMaxVisitors() == 200);
        assertThat(location.getMaxStages() == 10);
        assertThat(location.getRent().isEqualTo(Money.of(500, EURO)));
        deleteLocation(name);
    }

    @Test
    void checkGetLocationById() {
        deleteLocation("TestID");
        // add test location
        Location location = locationManagement.createLocation("TestID", 200, 10, Money.of(500, EURO));

        // find by id and check for not null and that the name is identical
        Location result = locationManagement.findById(location.getId());
        assertThat(result != null);
        assertThat(location.getName().equals("TestID"));
        deleteLocation("TestID");
    }
}
