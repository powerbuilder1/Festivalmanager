package festivalmanager.festival;

import static org.assertj.core.api.Assertions.*;

import static org.salespointframework.core.Currencies.*;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Streamable;

import festivalmanager.location.Location;
import festivalmanager.location.LocationManagement;

@SpringBootTest
@AutoConfigureMockMvc
public class FestivalMangementIntegrationTests {

    @Autowired
    private FestivalManagement festivalManagement;

    @Autowired
    private LocationManagement locationManagement;

    @Test
    void findsAllFestivals() {
        Streamable<Festival> festivals = festivalManagement.findAllFestivals();
        assertThat(festivals).hasSize(2);
    }

    @Test
    void checkFestivalContent() {
        // create test entries
        Location location = locationManagement.createLocation("Test", 200, 10, Money.of(500, EURO));
        Festival festival = festivalManagement.createFestival("Test", location, "2022-10-10", "2022-11-11",
                "Test Information");

        // find test entries
        Streamable<Festival> festivals = festivalManagement.findAllByName("Test");
        assertThat(festivals).hasSize(1);

        // check content
        Festival result = festivals.toList().get(0);
        assertThat(result.getName()).isEqualTo(festival.getName());
        assertThat(result.getLocation().getName()).isEqualTo(festival.getLocation().getName());
        assertThat(result.getBeginDate()).isEqualTo(festival.getBeginDate());
        assertThat(result.getEndDate()).isEqualTo(festival.getEndDate());
        assertThat(result.getInformation()).isEqualTo(festival.getInformation());
    }

    @Test
    void checkGetFestivalById() {

        // create test entries
        Location location = locationManagement.createLocation("TestID", 200, 10, Money.of(500, EURO));
        Festival festival = festivalManagement.createFestival("TestID", location, "2022-10-10", "2022-11-11",
                "Test Information");

        Festival result = festivalManagement.findById(festival.getId());
        assertThat(result != null);
        assertThat(result.getName().equals(festival.getName()));
    }

}
