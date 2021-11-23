package festivalmanager.location;

import static org.assertj.core.api.Assertions.*;

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
    void findsAllLocations() {
        Streamable<Location> locations = locationManagement.findAllLocations();
        assertThat(locations).hasSize(5);
    }

}
