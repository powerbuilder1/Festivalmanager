package festivalmanager.Ticket;

import com.mysema.commons.lang.Assert;

import festivalmanager.festival.Festival;
import festivalmanager.location.Location;
import org.javamoney.moneta.Money;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class TicketManagement {

	private static TicketRepository ticketRepository;

	public TicketManagement(TicketRepository ticketRepository) {
		Assert.notNull(ticketRepository, "TicketRepository must not be null!");
		this.ticketRepository = ticketRepository;
	}

	public static Ticket createTicket(Ticket ticket) {
		org.springframework.util.Assert.notNull(ticket, "ticket must not be null");
		return ticketRepository.save(ticket);
	}

	public Ticket createTicket(Festival festival, Money price, String barcode, double validePeriod) {
		Ticket ticket = new TagesKarte(festival, price, barcode, validePeriod);
		return createTicket(ticket);
	}

	public Streamable<Ticket> findInRepository (String barcode) {
		return ticketRepository.findAll().filter(Ticket -> Ticket.getBarcode().equals(barcode));
	}




}

