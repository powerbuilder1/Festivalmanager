package festivalmanager.Ticket;

import java.util.List;

import festivalmanager.festival.FestivalManagement;
import festivalmanager.lineup.LineUpManagement;
import org.javamoney.moneta.Money;
import static org.salespointframework.core.Currencies.*;
import org.salespointframework.core.DataInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;




@Component
public class TicketDataInitializer implements DataInitializer {

	private final Logger LOG = LoggerFactory.getLogger(festivalmanager.Ticket.TicketDataInitializer.class);
	private final FestivalManagement festivalManagement;
	private final TicketManagement ticketManagement;
	TicketDataInitializer (TicketManagement ticketManagement, FestivalManagement festivalManagement) {
		Assert.notNull(ticketManagement, "festivalManagement must not be null!");
		this.ticketManagement = ticketManagement;
		this.festivalManagement = festivalManagement;
	}
	@Override
	public void initialize() {
		LOG.info("Initializing data for {}", getClass().getSimpleName());
		LOG.info("Creating default  entries.");
		List.of(new TagesKarte(festivalManagement.findAllByName("Weihnachtsfestival").toList().get(0),Money.of(9.99, EURO)," 12334fr191", 24),
				new TagesKarte(festivalManagement.findAllByName("Weihnachtsfestival").toList().get(0),Money.of(9.99, EURO)," 12334fr191", 24),
				new TagesKarte(festivalManagement.findAllByName("Weihnachtsfestival").toList().get(0),Money.of(9.99, EURO)," 1233thgh191", 24),
				new TagesKarte(festivalManagement.findAllByName("Weihnachtsfestival").toList().get(0),Money.of(9.99, EURO)," 12334fr11", 24),
				new TagesKarte(festivalManagement.findAllByName("Weihnachtsfestival").toList().get(0),Money.of(9.99, EURO)," 1233434t", 24),
				new TagesKarte(festivalManagement.findAllByName("Weihnachtsfestival").toList().get(0),Money.of(9.99, EURO)," 1233434tgh", 24),
				new TagesKarte(festivalManagement.findAllByName("Weihnachtsfestival").toList().get(0),Money.of(9.99, EURO)," 1233434thz", 24),
				new TagesKarte(festivalManagement.findAllByName("Weihnachtsfestival").toList().get(0),Money.of(9.99, EURO)," 1233434thf", 24),
				new TagesKarte(festivalManagement.findAllByName("Weihnachtsfestival").toList().get(0),Money.of(9.99, EURO)," 1233434thw", 24)).forEach(TicketManagement::createTicket);

	}
}

