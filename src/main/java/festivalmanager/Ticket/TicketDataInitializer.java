package festivalmanager.Ticket;

import festivalmanager.festival.FestivalManagement;
import org.javamoney.moneta.Money;
import org.salespointframework.core.DataInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import static org.salespointframework.core.Currencies.EURO;

@Component
@Order(60)
public class TicketDataInitializer implements DataInitializer {

	private static final Logger LOG = LoggerFactory.getLogger(TicketDataInitializer.class);

	private final TicketRepository ticketRepository;
	private final FestivalManagement festivalManagement;


	/**
	 * Constructor for the TicketDataInitializer
	 * @param ticketRepository the ticket repository
	 * @param festivalManagement the festival management

	 */
	TicketDataInitializer(TicketRepository ticketRepository, FestivalManagement festivalManagement) {

		Assert.notNull(ticketRepository, "TicketRepository must not be null!");
		Assert.notNull(festivalManagement, "festivalManagement must not be null!");



		this.ticketRepository = ticketRepository;
		this.festivalManagement = festivalManagement;



	}

	/**
	 * Initializes the tickets if no data is present.
	 */
	@Override
	public void initialize() {
		if (ticketRepository.findAll().iterator().hasNext()) {
			return;
		}

		LOG.info("Creating default catalog entries.");
		if (!festivalManagement.findAllFestivals().isEmpty()) {
			ticketRepository.save(new Ticket("Abendkasse", Money.of(10, EURO), Ticket.TicketType.ABENDKASSE,
				festivalManagement.findAllByName("Weihnachtsfestival").toList().get(0), 30));
			ticketRepository.save(new Ticket("Campingticket", Money.of(10, EURO), Ticket.TicketType.CAMPINGTICKET,
				festivalManagement.findAllByName("Weihnachtsfestival").toList().get(0), 40));
			ticketRepository.save(new Ticket("Tages Karte", Money.of(10, EURO), Ticket.TicketType.TAGESKARTE,
				festivalManagement.findAllByName("Weihnachtsfestival").toList().get(0), 30));
			ticketRepository.save(new Ticket("Abendkasse", Money.of(10, EURO), Ticket.TicketType.ABENDKASSE,
				festivalManagement.findAllByName("Maifeld Derby 2021").toList().get(0), 30));
			ticketRepository.save(new Ticket("CampingTicket", Money.of(10, EURO), Ticket.TicketType.CAMPINGTICKET,
				festivalManagement.findAllByName("Maifeld Derby 2021").toList().get(0), 40));
			ticketRepository.save(new Ticket("Tages Karte", Money.of(10, EURO), Ticket.TicketType.TAGESKARTE,
				festivalManagement.findAllByName("Maifeld Derby 2021").toList().get(0), 30));
		}
	}
}

