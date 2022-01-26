package festivalmanager.Lineup;

import static org.assertj.core.api.Assertions.*;

import static org.salespointframework.core.Currencies.*;

import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.lineup.Band;
import festivalmanager.lineup.LineUp;
import festivalmanager.lineup.LineUpManagement;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Streamable;

import festivalmanager.location.Location;
import festivalmanager.location.LocationManagement;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@AutoConfigureMockMvc
public class LineUpIntegrationTests {

	@Autowired
	private LineUpManagement lineUpManagement;
	@Autowired
	private FestivalManagement festivalManagement;
	@Autowired
	private LocationManagement locationManagement;


	void deleteLineUp (String name) {
		Streamable<LineUp> lineUps = lineUpManagement.findLineUpByFestivalName(name);
		for(LineUp lineUp : lineUps)
		{
			lineUpManagement.deleteById(lineUp.getId());
		}
	}
	void deleteFestival(String name) {
		Streamable<Festival> festivals = festivalManagement.findAllByName(name);
		for(Festival festival : festivals)
		{
			festivalManagement.deleteById(festival.getId());
		}
	}
	void deleteLocation(String name) {
		Streamable<Location> locations = locationManagement.findAllByName(name);
		for(Location location : locations)
		{
			locationManagement.deleteById(location.getId());
		}
	}



	@Test
	void findsAllLineUps() {
		Streamable<LineUp> lineUps = lineUpManagement.findAllLineUp();
		assertThat(lineUps).hasSizeGreaterThan(-1);
	}

	@Test
	void checkLineUpContent() throws Exception {

		deleteFestival("Test");
		deleteLocation("Test");

		// create test entries
		Location location = locationManagement.createLocation("Test", 200, 10, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("Test", location, "2022-10-10", "2022-11-11",
				"Test Information");
		Band band = new Band("Test",Money.of(2000, EURO),"Buehne 1", "09:00 - 11:00");

		LineUp lineUp =  new LineUp(festivalManagement.findAllByName("Test").toList().get(0) );
		lineUp.addBandto(band);

		lineUpManagement.createLineUp(lineUp);


		// find test entries
		Streamable<LineUp> lineUps = lineUpManagement.findLineUpByFestivalName(festival.getName());
		assertThat(lineUps).hasSize(1);

		LineUp result = lineUpManagement.findById(lineUp.getId());
		assertThat(result.getId()).isEqualTo(lineUp.getId());
		assertThat(result.getFestival().equals(lineUp.getFestival()));
		assertThat(result.getBands().equals(lineUp.getBands()));


		// delete test entries

		deleteFestival("Test");
		deleteLocation("Test");

	}

	@Test
	void checkGetLineUpById() throws Exception {
		deleteFestival("TestID");
		deleteLocation("TestID");

		// create test entries
		Location location = locationManagement.createLocation("TestID", 200, 10, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("TestID", location, "2022-10-10", "2022-11-11",
				"Test Information");
		LineUp lineUp = lineUpManagement.createLineUp(festival);

		LineUp result = lineUpManagement.findById(lineUp.getId());
		assertThat(result != null);
		assertThat(result.getId()).isEqualTo(lineUp.getId());

		// delete test entries
		deleteFestival("TestID");
		deleteLocation("TestID");
	}
	@Test
	void checkBands() throws Exception {

		deleteFestival("TestBands");
		deleteLocation("TestBands");

		// create test entries
		Location location = locationManagement.createLocation("TestBands", 200, 10, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("TestBands", location, "2022-10-10", "2022-11-11",
				"Test Information");
		Band band = new Band("TestBand",Money.of(2000, EURO),"Buehne 2", "09:00 - 11:00");

		LineUp lineUp =  new LineUp(festivalManagement.findAllByName("TestBands").toList().get(0) );
		lineUp.addBandto(band);

		lineUpManagement.createLineUp(lineUp);


		// find test entries
		Band bands = lineUpManagement.findBandByName(lineUp.getId(),band.name1);
		assertThat(bands.equals(band));
		assertThat(bands.getName1()).isEqualTo(band.getName1());
		assertThat(bands.getStage()).isEqualTo(band.getStage());
		assertThat(bands.getPerformanceHour()).isEqualTo(band.getPerformanceHour());
		assertThat(bands.getPrice()).isEqualTo(band.getPrice());


		// delete test entries

		deleteFestival("Test");
		deleteLocation("Test");

	}



}


