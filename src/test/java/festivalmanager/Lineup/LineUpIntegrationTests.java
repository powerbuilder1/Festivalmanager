package festivalmanager.festival;

import static org.assertj.core.api.Assertions.*;

import static org.salespointframework.core.Currencies.*;

import festivalmanager.lineup.Band;
import festivalmanager.lineup.LineUp;
import festivalmanager.lineup.LineUpManagement;
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
public class LineUpIntegrationTests {

	@Autowired
	private FestivalManagement festivalManagement;

	@Autowired
	private LineUpManagement lineUpManagement;

	@Autowired
	private LocationManagement locationManagement;

	@Test
	void findsAllLineUps() {
		Streamable<LineUp> lineUps = lineUpManagement.findAllLineUp();
		assertThat(lineUps).hasSize(2);
	}
	@Test
	void checkGetLineUpById() {

		// create test entries
		Location location = locationManagement.createLocation("TestID", 200, 10, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("TestID", location, "2022-10-10", "2022-11-11",
				"Test Information");
		Band band1 = new Band("TestBand", Money.of(2000, EURO),"Buehne 2", "14:00 - 16:00");
		LineUp lineUp = lineUpManagement.createLineUp(festival);
		lineUp.addBandto(band1);

		LineUp result = lineUpManagement.findById(lineUp.getId());
		assertThat(result != null);
		assertThat(result.getFestival().equals(lineUp.getFestival()));

	}

}

