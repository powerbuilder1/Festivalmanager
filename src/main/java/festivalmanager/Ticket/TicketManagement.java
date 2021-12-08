package festivalmanager.Ticket;

import com.mysema.commons.lang.Assert;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class TicketManagement {

	private final TicketRepository ticketRepository;

	public TicketManagement(TicketRepository ticketRepository) {
		Assert.notNull(ticketRepository, "TicketRepository must not be null!");
		this.ticketRepository = ticketRepository;
	}

	public Streamable<Ticket> findInRepository (String barcode) {
		return ticketRepository.findAll().filter(Ticket -> Ticket.getBarcode().equals(barcode));
	}




}

