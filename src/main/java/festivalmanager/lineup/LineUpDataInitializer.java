package festivalmanager.lineup;


import java.util.List;
import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.location.LocationManagement;
import org.javamoney.moneta.Money;
import org.salespointframework.core.DataInitializer;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import static org.salespointframework.core.Currencies.EURO;

@Component
public class LineUpDataInitializer implements DataInitializer {
	private final Logger LOG = LoggerFactory.getLogger(festivalmanager.lineup.LineUpDataInitializer.class);
	private final LineUpManagement lineUpManagement;
	private final FestivalManagement festivalManagement;

	LineUpDataInitializer (LineUpManagement lineUpManagement,FestivalManagement festivalManagement) {
		Assert.notNull(lineUpManagement, "festivalManagement must not be null!");
		this.lineUpManagement = lineUpManagement;
		this.festivalManagement = festivalManagement;
	}
	@Override
	public void initialize() {
		LOG.info("Initializing data for {}", getClass().getSimpleName());
		LOG.info("Creating default  entries.");
		Band band1 = new Band("The Strokes", Money.of(2000, EURO),"Buehne 2", "14:00 - 16:00");
		Band band2 = new Band("The Parcels",Money.of(2000, EURO),"Buehne 3", "20:00 - 22:00");
		Band band3 = new Band("Boy Pablo",Money.of(3000, EURO),"Buehne 1", "19:00 - 21:00");
		Band band4 = new Band("Kings of Leon",Money.of(4000, EURO),"Buehne 3", "12:00 - 14:00");
		Band band5 = new Band("TV Girl",Money.of(6000, EURO),"Buehne 2", "18:00 - 20:00");
		Band band6 = new Band("Kendrick Lamar",Money.of(3000, EURO),"Buehne 1", "09:00 - 11:00");
		LineUp lineUp1 = new LineUp(festivalManagement.findAllByName("Weihnachtsfestival").toList().get(0),festivalManagement.findAllByName("Weihnachtsfestival").toList().get(0).getId() );
		LineUp lineUp2 = new LineUp(festivalManagement.findAllByName("Maifeld Derby 2021").toList().get(0) ,festivalManagement.findAllByName("Maifeld Derby 2021").toList().get(0).getId());
		lineUp1.addBandto(band1);
		lineUp1.addBandto(band2);
		lineUp1.addBandto(band3);
		lineUp2.addBandto(band4);
		lineUp2.addBandto(band5);
		lineUp2.addBandto(band6);
		List.of(lineUp1,lineUp2).forEach(LineUpManagement::createLineUp);




	}
}