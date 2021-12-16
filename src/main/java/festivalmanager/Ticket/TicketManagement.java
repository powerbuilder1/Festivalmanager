package festivalmanager.Ticket;

import festivalmanager.TicketStock.TicketStockManagement;
import festivalmanager.authentication.UserManagement;
import festivalmanager.catering.Food;
import festivalmanager.catering.NewFoodItemForm;
import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import org.javamoney.moneta.Money;
import org.salespointframework.core.Currencies;
import org.salespointframework.useraccount.UserAccount;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class TicketManagement {
	private static TicketRepository ticketRepository;
	private static TicketStockManagement ticketStockManagement;
	private static FestivalManagement festivalManagement;
	private final UserManagement userManagement;

	TicketManagement(TicketRepository ticketRepository ,UserManagement userManagement, TicketStockManagement ticketStockManagement, FestivalManagement festivalManagement) {
		Assert.notNull(ticketRepository, "ticketRepository must not be null");
		Assert.notNull(userManagement, "userManagement must not be null");
		Assert.notNull(festivalManagement, "festivalManagement must not be null");
		Assert.notNull(ticketStockManagement, "ticketStockManagement must not be null");

		this.ticketRepository = ticketRepository;
		this.userManagement = userManagement;
		this.ticketStockManagement = ticketStockManagement;
		this.festivalManagement = festivalManagement;

	}

	// get catalog
	public Streamable<Ticket> getTicketCatalog(Festival festival) {

		return ticketRepository.findTicketByFestival(festival);


	}
	// add ticket to stock
	public void addTicketToCatalog(TicketForm ticketForm) throws Exception {

		if (ticketForm.getFestival() == null) {
			ticketForm.setFestival(festivalManagement.findById(ticketForm.getFestivalIdentifier()));}

		double sum = 0;
		for ( Ticket ticket : ticketRepository.findAll())
		{
			if (ticket.getFestival() == ticketForm.getFestival())
				{
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
		}
		else
		{
			throw new Exception("There can not be more tickets than the maximal number of persons in the location , sorry");
		}
		}

		public Streamable<Ticket> getAllTicketCatalog() {

		return ticketRepository.findAll();


	}


}
