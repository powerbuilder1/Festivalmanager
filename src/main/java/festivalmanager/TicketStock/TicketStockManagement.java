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
	// find a UniqueInventoryItem by Food-Product
	public Optional<TicketInventoryItem> findByProduct(Ticket ticket) {
		return ticketStockInventory.findByProduct(ticket);
	}

	// initialize new Inventory Item for a specific Food-Product
	public void initializeInventoryItem(Ticket ticket, double amount, Festival festival) {
		ticketStockInventory.save(new TicketInventoryItem(ticket, Quantity.of(amount), festival));
	}
	// Test
	public void deleteAll() {
		ticketStockInventory.deleteAll();
	}


	}

