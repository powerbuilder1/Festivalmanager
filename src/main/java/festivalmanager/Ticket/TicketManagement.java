package festivalmanager.Ticket;

import festivalmanager.authentication.UserManagement;
import festivalmanager.catering.Food;
import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import org.salespointframework.useraccount.UserAccount;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class TicketManagement {
	private static TicketRepository ticketRepository;
	private static FestivalManagement festivalManagement;
	private final UserManagement userManagement;

	TicketManagement(TicketRepository ticketRepository ,UserManagement userManagement) {
		Assert.notNull(ticketRepository, "ticketRepository must not be null");
		Assert.notNull(userManagement, "userManagement must not be null");

		this.ticketRepository = ticketRepository;
		this.userManagement = userManagement;

	}
	public void setFestivalManagement(FestivalManagement festivalManagement) {
		this.festivalManagement = festivalManagement;
	}
	// get catalog
	public Streamable<Ticket> getTicketCatalog(Festival festival) {

		return ticketRepository.findTicketByFestival(festival);


	}


}
