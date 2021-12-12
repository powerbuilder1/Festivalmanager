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


}

