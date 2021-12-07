package festivalmanager.location;

import static org.assertj.core.api.Assertions.*;

import static org.salespointframework.core.Currencies.*;

import javax.annotation.meta.Exhaustive;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Streamable;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationManagementIntegrationTests {

    @Autowired
    private LocationManagement locationManagement;

    @Test
    void createLocation() {
        LocationForm form = new LocationForm(12, 1, "TestLocation", 500, 200);
        locationManagement.createLocation(form);
        assertThat(locationManagement.findAllByName(form.getName())).hasSize(1);
    }

    @Test
    void findsAllLocations() {
        Streamable<Location> locations = locationManagement.findAllLocations();
        assertThat(locations).hasSize(5);
    }

    @Test
    void checkLocationContent() {
        // add test location
        locationManagement.createLocation("Test", 200, 10, Money.of(500, EURO));

        // find location
        Streamable<Location> locations = locationManagement.findAllByName("Test");
        assertThat(locations).hasSize(1);

        // check its contents
        Location location = locations.toList().get(0);
        assertThat(location.getName()).isEqualTo("Test");
        assertThat(location.getMaxVisitors() == 200);
        assertThat(location.getMaxStages() == 10);
        assertThat(location.getRent().isEqualTo(Money.of(500, EURO)));
    }

    @Test
    void checkGetLocationById() {

        // add test location
        Location location = locationManagement.createLocation("TestID", 200, 10, Money.of(500, EURO));

        // find by id and check for not null and that the name is identical
        Location result = locationManagement.findById(location.getId());
        assertThat(result != null);
        assertThat(location.getName().equals("TestID"));
    }
}
