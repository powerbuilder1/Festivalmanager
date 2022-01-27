package festivalmanager.Lineup;

import static org.assertj.core.api.Assertions.*;

import static org.salespointframework.core.Currencies.*;

import festivalmanager.AbstractIntegrationTests;
import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.lineup.Band;
import festivalmanager.lineup.BandForm;
import festivalmanager.lineup.LineUp;
import festivalmanager.lineup.LineUpManagement;
import org.assertj.core.api.Assert;
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

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

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
		deleteLineUp("Test");
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
		deleteLineUp("Test");
		deleteFestival("Test");
		deleteLocation("Test");

	}

	@Test
	void checkGetLineUpById() throws Exception {
		deleteLineUp("TestID");
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
		deleteLineUp("TestID");
		deleteFestival("TestID");
		deleteLocation("TestID");
	}
	@Test
	void checkBandsInLineUp() throws Exception {
		deleteLineUp("TestBands");

		deleteFestival("TestBands");
		deleteLocation("TestBands");

		// create test entries
		Location location = locationManagement.createLocation("TestBands", 200, 10, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("TestBands", location, "2022-10-10", "2022-11-11",
				"Test Information");
		Band band = new Band("TestBand1", Money.of(2000, EURO), "Buehne 1", "09:00 - 11:00");
		Band band1 = new Band("TestBand2", Money.of(2000, EURO), "Buehne 2", "11:00 - 13:00");
		Band band2 = new Band("TestBand3", Money.of(2000, EURO), "Buehne 3", "13:00 - 15:00");
		Band band3 = new Band("TestBand4", Money.of(2000, EURO), "Buehne 4", "15:00 - 17:00");

		LineUp lineUp = new LineUp(festivalManagement.findAllByName("TestBands").toList().get(0));
		lineUp.addBandto(band);
		lineUp.addBandto(band1);
		lineUp.addBandto(band2);
		lineUp.addBandto(band3);

		lineUpManagement.createLineUp(lineUp);

		Streamable<LineUp> lineups = lineUpManagement.findLineUpByFestivalName("TestBands");
		LineUp result = lineups.toList().get(0);
		assertThat(result != null);
		assertThat(result.getBands().size()).isEqualTo(lineUp.getBands().size());
		assertThat(result.getBands().equals(lineUp.getBands()));
		assertThat(result.getStages()).isEqualTo(lineUp.getStages());
		assertThat(result.getBandnames()).isEqualTo(lineUp.getBandnames());
		assertThat(result.getLineupUhrzeiten()).isEqualTo(lineUp.getLineupUhrzeiten());

		deleteLineUp("TestBands");
		deleteFestival("TestBands");
		deleteLocation("TestBands");


	}

	@Test
	void checkBands() throws Exception {
		deleteLineUp("TestOnlyBand");

		deleteFestival("TestOnlyBand");
		deleteLocation("TestOnlyBand");

		// create test entries
		Location location = locationManagement.createLocation("TestOnlyBand", 200, 10, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("TestOnlyBand", location, "2022-10-10", "2022-11-11",
				"Test Information");
		Band band = new Band("TestOnlyBand",Money.of(2000, EURO),"Buehne 2", "09:00 - 11:00");

		LineUp lineUp =  new LineUp(festivalManagement.findAllByName("TestOnlyBand").toList().get(0) );
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
		deleteLineUp("TestOnlyBand");
		deleteFestival("TestOnlyBand");
		deleteLocation("TestOnlyBand");

	}
	@Test
	void checkAddBandstoLineUp() throws Exception {
		deleteLineUp("TestAddBands");

		deleteFestival("TestAddBands");
		deleteLocation("TestAddBands");

		// create test entries
		Location location = locationManagement.createLocation("TestAddBands", 300, 15, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("TestAddBands", location, "2022-10-10", "2022-11-11",
				"Test Information");
		Band band = new Band("TestBand1", Money.of(2000, EURO), "Buehne 1", "09:00 - 11:00");
		Band band1 = new Band("TestBand2", Money.of(2000, EURO), "Buehne 2", "11:00 - 13:00");
		LineUp lineUp = new LineUp(festivalManagement.findAllByName("TestAddBands").toList().get(0));
		lineUp.addBandto(band);
		lineUp.addBandto(band1);

		lineUpManagement.createLineUp(lineUp);
		Band addedBand = new Band("AddedBand", Money.of(2000, EURO), "Buehne 3", "13:00 - 14:00");
		BandForm addedBandForm = new BandForm( addedBand.getName1(),addedBand.getPrice().getNumber().doubleValueExact(),addedBand.getStage(),addedBand.getPerformanceHour(), addedBand.getId());
		lineUpManagement.addBand(lineUp.getId(),addedBandForm);

		System.out.println(lineUp.getBands().size());

		Streamable<LineUp> lineups = lineUpManagement.findLineUpByFestivalName("TestAddBands");
		LineUp result = lineups.toList().get(0);
		assertThat(result != null);

		Band addedBandResult = lineUpManagement.findBandByName(result.getId(),addedBand.getName1());

		assertThat(addedBandResult.equals(addedBand));

		deleteLineUp("TestAddBands");

		deleteFestival("TestAddBands");
		deleteLocation("TestAddBands");


	}
	@Test
	void checkDeleteBandsFromLineUp() throws Exception {
		deleteLineUp("TestDeleteBands");

		deleteFestival("TestDeleteBands");
		deleteLocation("TestDeleteBands");

		// create test entries
		Location location = locationManagement.createLocation("TestDeleteBands", 400, 10, Money.of(550, EURO));
		Festival festival = festivalManagement.createFestival("TestDeleteBands", location, "2022-10-10", "2022-11-11",
				"Test Information");
		Band band = new Band("TestDelBand1", Money.of(2000, EURO), "Buehne 1", "09:00 - 11:00");
		Band band1 = new Band("TestDelBand2", Money.of(2000, EURO), "Buehne 2", "11:00 - 13:00");
		Band band2 = new Band("TestDelBand3", Money.of(2000, EURO), "Buehne 3", "13:00 - 15:00");

		LineUp lineUp = new LineUp(festivalManagement.findAllByName("TestDeleteBands").toList().get(0));
		lineUp.addBandto(band);
		lineUp.addBandto(band1);
		lineUp.addBandto(band2);

		lineUpManagement.createLineUp(lineUp);

		lineUpManagement.deleteBand(lineUp.getId(), band.getName1());

		Streamable<LineUp> lineups = lineUpManagement.findLineUpByFestivalName("TestDeleteBands");
		LineUp result = lineups.toList().get(0);
		assertThat(result != null);

		for (Band wantedBand : result.getBands())
		{
			assertThat(wantedBand.equals(band)== false);
		}



		deleteLineUp("TestDeleteBands");

		deleteFestival("TestDeleteBands");
		deleteLocation("TestDeleteBands");


	}
	@Test
	void checkEditBandsFromLineUp() throws Exception {

		deleteLineUp("TestEditBands");
		deleteFestival("TestEditBands");
		deleteLocation("TestEditBands");

		// create test entries
		Location location = locationManagement.createLocation("TestEditBands", 400, 10, Money.of(550, EURO));
		Festival festival = festivalManagement.createFestival("TestEditBands", location, "2022-10-10", "2022-11-11",
				"Test Information");
		Band band = new Band("TestEdBand1", Money.of(2000, EURO), "Buehne 1", "09:00 - 11:00");
		Band band1 = new Band("TestEdBand2", Money.of(2000, EURO), "Buehne 2", "11:00 - 13:00");
		Band band2 = new Band("TestEdBand3", Money.of(2000, EURO), "Buehne 3", "13:00 - 15:00");

		LineUp lineUp = new LineUp(festivalManagement.findAllByName("TestEditBands").toList().get(0));
		lineUp.addBandto(band);
		lineUp.addBandto(band1);
		lineUp.addBandto(band2);

		lineUpManagement.createLineUp(lineUp);
		BandForm editedBandForm = new BandForm( band.getName1(),2000 ,"Buehne 4","15:00 - 17:00", band.getId());



		lineUpManagement.updateBand(lineUp.getId(), editedBandForm);

		Streamable<LineUp> lineups = lineUpManagement.findLineUpByFestivalName("TestEditBands");
		LineUp result = lineups.toList().get(0);
		assertThat(result != null);

		Band editedBandResult = lineUpManagement.findBandByName(result.getId(),band.getName1());
		assertThat(editedBandResult.getName1().equals(editedBandForm.getName()));
		assertThat((editedBandResult.getPrice().getNumber().doubleValueExact())).isEqualTo(editedBandForm.getPrice());
		assertThat((editedBandResult.getPerformanceHour().equals(editedBandForm.getPerformanceHour())));
		assertThat((editedBandResult.getStage().equals(editedBandForm.getStage())));
		assertThat((editedBandResult.getId().equals(editedBandForm.getId())));







		deleteLineUp("TestEditBands");
		deleteFestival("TestEditBands");
		deleteLocation("TestEditBands");


	}



}


