package festivalmanager.TicketStock;

import festivalmanager.Ticket.Ticket;
import festivalmanager.authentication.User;
import festivalmanager.authentication.UserManagement;
import festivalmanager.communication.CommunicationManagement;
import festivalmanager.communication.Room;
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
	private final CommunicationManagement communicationManagement;
	/**
	 * Constructor
	 *
	 * @param ticketStockInventory
	 * @param userManagement

	 */
	public TicketStockManagement(TicketStockInventory ticketStockInventory, UserManagement userManagement, CommunicationManagement communicationManagement) {
		this.ticketStockInventory = ticketStockInventory;
		this.userManagement = userManagement;
		this.communicationManagement = communicationManagement;
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
	* @param name
	 * @return
	 */
	public Streamable<TicketInventoryItem> getTicketStockbyfestival ( String name ){
		Streamable<TicketInventoryItem> ticketStockOfFestival = ticketStockInventory.findAll().filter(ticketStock -> ticketStock.getFestival().getName().equals(name));

		User userTicket = userManagement.findByName("manager");
		Room room = communicationManagement.findRoomByName("public");

		for (TicketInventoryItem ticketsOfStock : ticketStockOfFestival)
		{

			if (ticketsOfStock.getQuantity().toString().equals("0")) {

				communicationManagement.sendMessage(userTicket,"there is no more tickets of "+ticketsOfStock.getProduct().getName() +" of the Festival "+ ticketsOfStock.getFestival().getName(),room);

			}


		}

		return ticketStockOfFestival;
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

