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

	// Test
	public void deleteAll() {
		ticketStockInventory.deleteAll();
	}


	}

