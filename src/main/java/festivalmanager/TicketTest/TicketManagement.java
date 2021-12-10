package festivalmanager.TicketTest;

import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.lineup.LineUp;
import festivalmanager.lineup.LineUpRepository;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


public class TicketManagement {
	private static TicketRepository ticketRepository;
	private static FestivalManagement festivalManagement;

	TicketManagement(TicketRepository ticketRepository ) {
		Assert.notNull(ticketRepository, "ticketRepository must not be null");

		this.ticketRepository = ticketRepository;

	}

	public void setFestivalManagement(FestivalManagement festivalManagement) {
		this.festivalManagement = festivalManagement;
	}
	public static Ticket createTicket(Ticket ticket) {
		Assert.notNull(ticket, "ticket must not be null");
		return ticketRepository.save(ticket);
	}

	public Ticket createTicket(String name, Money price, Ticket.TicketType type, Festival festival, Double percentOf ) {
		Ticket ticket = new Ticket(name,price,type,festival,percentOf);
		return createTicket(ticket);
	}
}
