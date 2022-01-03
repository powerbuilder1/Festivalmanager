package festivalmanager.TicketStock;

import festivalmanager.Ticket.Ticket;
import festivalmanager.authentication.User;
import festivalmanager.authentication.UserManagement;
import festivalmanager.catering.Food;
import festivalmanager.festival.Festival;
import festivalmanager.stock.FoodInventoryItem;
import org.salespointframework.quantity.Quantity;
import org.salespointframework.useraccount.UserAccount;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TicketStockManagement {
	private final TicketStockInventory ticketStockInventory;
	private final UserManagement userManagement;

	public TicketStockManagement(TicketStockInventory ticketStockInventory, UserManagement userManagement) {
		this.ticketStockInventory = ticketStockInventory;
		this.userManagement = userManagement;
	}

	// get current stock
	public Streamable<TicketInventoryItem> getCurrentTicketStock(Optional<UserAccount> userAccount) {
		if (userAccount.isPresent()) {
			User user = userManagement.findUserByUserAccount(userAccount.get());
			return ticketStockInventory.findTicketInventoryItemByFestival(user.getFestival());
		}
		return null;
	}
	public Streamable<TicketInventoryItem> getAllTicketStock() {

			return ticketStockInventory.findAll();

	}
	public Streamable<TicketInventoryItem> getTicketStockbyfestival ( Festival festival)
	{
		return ticketStockInventory.findAll().filter(ticketStock -> ticketStock.getFestival().equals(festival));
	}
	// initialize new Inventory Item for a ticket of specific Festival
	public void initializeNewTicketInInventory(Ticket ticketItem, double amount, Festival festival) {
		ticketStockInventory.save(new TicketInventoryItem(ticketItem, Quantity.of(amount), festival));
	}
	// Test
	public void deleteAll() {
		ticketStockInventory.deleteAll();
	}


	}

