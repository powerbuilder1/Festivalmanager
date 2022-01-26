package festivalmanager.Ticket;

import festivalmanager.TicketStock.TicketStockManagement;
import festivalmanager.authentication.UserManagement;
import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import org.javamoney.moneta.Money;
import org.salespointframework.core.Currencies;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


@Service
public class TicketManagement {
	private static TicketRepository ticketRepository;
	private static TicketStockManagement ticketStockManagement;
	private static FestivalManagement festivalManagement;
	private final UserManagement userManagement;
	/**
	 * Constructor
	 *
	 * @param ticketRepository
	 * @param userManagement
	 * @param ticketStockManagement
	 * @param festivalManagement

	 */
	TicketManagement(TicketRepository ticketRepository ,UserManagement userManagement,
		TicketStockManagement ticketStockManagement, FestivalManagement festivalManagement) {
		Assert.notNull(ticketRepository, "ticketRepository must not be null");
		Assert.notNull(userManagement, "userManagement must not be null");
		Assert.notNull(festivalManagement, "festivalManagement must not be null");
		Assert.notNull(ticketStockManagement, "ticketStockManagement must not be null");

		this.ticketRepository = ticketRepository;
		this.userManagement = userManagement;
		this.ticketStockManagement = ticketStockManagement;
		this.festivalManagement = festivalManagement;

	}

	/**
	 * get catalog of tickets by a festival

	 * @param name
	 * @return
	 */
	public Streamable<Ticket> getTicketCatalog(String name) {
		return ticketRepository.findAll().filter(ticket -> ticket.getFestival().getName().equals(name));
	}
	/**
	 * add item to stock of tickets of a festival
	 * @param ticketForm

	 */
	public void addTicketToCatalog(TicketForm ticketForm) throws IllegalStateException {

		if (ticketForm.getFestival() == null) {
			ticketForm.setFestival(festivalManagement.findById(ticketForm.getFestivalIdentifier()));
		}

		double sum = 0;
		for ( Ticket ticket : ticketRepository.findAll()) {
			if (ticket.getFestival() == ticketForm.getFestival()) {
				sum = sum + ticket.getPercentOf();
			}
		}
		sum = sum + ticketForm.getPercentOf();
		if(sum <= 100) {
			Ticket ticketItem = ticketRepository.save(new Ticket(
					ticketForm.getName(),
					Money.of(ticketForm.getPrice(), Currencies.EURO),
					ticketForm.getTicketType(), ticketForm.getFestival(), ticketForm.getPercentOf()));

			ticketStockManagement.initializeNewTicketInInventory(ticketItem, ticketItem.count, ticketForm.getFestival());
		} else {
			throw new IllegalStateException("There can not be more tickets than the maximal number of persons in the location");
		}
	}
	/**
	 * get catalog of tickets of all the festivals
	 * @return
	 */
	public Streamable<Ticket> getAllTicketCatalog() {
		return ticketRepository.findAll();
	}
}

