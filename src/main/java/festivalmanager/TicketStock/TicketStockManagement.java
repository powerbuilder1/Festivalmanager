package festivalmanager.TicketStock;

import festivalmanager.Ticket.Ticket;
import festivalmanager.authentication.User;
import festivalmanager.authentication.UserManagement;
import festivalmanager.festival.Festival;
import org.salespointframework.quantity.Quantity;
import org.salespointframework.useraccount.UserAccount;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketStockManagement {
	private final TicketStockInventory ticketStockInventory;
	private final UserManagement userManagement;
	/**
	 * Constructor
	 *
	 * @param ticketStockInventory
	 * @param userManagement

	 */
	public TicketStockManagement(TicketStockInventory ticketStockInventory, UserManagement userManagement) {
		this.ticketStockInventory = ticketStockInventory;
		this.userManagement = userManagement;
	}

	/**
	 * get all current stocks of all festivals

	 * @return
	 */
	public Streamable<TicketInventoryItem> getAllTicketStock() {
			return ticketStockInventory.findAll();
	}
	/**
	 * get all current stocks by festival
	* @param festival
	 * @return
	 */
	public Streamable<TicketInventoryItem> getTicketStockbyfestival ( Festival festival){
		return ticketStockInventory.findAll().filter(ticketStock -> ticketStock.getFestival().equals(festival));
	}

	/**
	 * initialize new Inventory Item for a ticket of specific Festival
	 * @param ticketItem
	 * @param amount
	 * @param festival

	 * @return
	 */
	public void initializeNewTicketInInventory(Ticket ticketItem, double amount, Festival festival) {
		ticketStockInventory.save(new TicketInventoryItem(ticketItem, Quantity.of(amount), festival));
	}

}

