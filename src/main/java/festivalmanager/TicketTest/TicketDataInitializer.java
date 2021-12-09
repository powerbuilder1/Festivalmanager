package festivalmanager.TicketTest;

import org.javamoney.moneta.Money;
import org.salespointframework.core.DataInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import static org.salespointframework.core.Currencies.EURO;

@Component
public class TicketDataInitializer implements DataInitializer {

	private static final Logger LOG = LoggerFactory.getLogger(TicketDataInitializer.class);

	private final TicketRepository ticketRepository;



	TicketDataInitializer(TicketRepository ticketRepository) {

		Assert.notNull(ticketRepository, "TicketRepository must not be null!");

		this.ticketRepository = ticketRepository;
	}


	@Override
	public void initialize() {
		if (ticketRepository.findAll().iterator().hasNext()) {
			return;
		}

		LOG.info("Creating default catalog entries.");

		ticketRepository.save(new Ticket("Abendkasse", Money.of(10, EURO), Ticket.TicketType.AbendKasse));
		ticketRepository.save(new Ticket("Tageskarte", Money.of(10, EURO), Ticket.TicketType.TagesKarte));
		ticketRepository.save(new Ticket("Camping Ticket",Money.of(10, EURO), Ticket.TicketType.CampingTicket));

	}

	}

