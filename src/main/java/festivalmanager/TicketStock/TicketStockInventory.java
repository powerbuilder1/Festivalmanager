package festivalmanager.TicketStock;

import festivalmanager.festival.Festival;
import org.salespointframework.inventory.UniqueInventory;
import org.springframework.data.util.Streamable;

/**
 * Stock of all the ticket items
 */
public interface TicketStockInventory extends UniqueInventory <TicketInventoryItem> {
	/**
	 * Returns tickets items
	 * @return Streamable of Ticket Items
	 */
	Streamable<TicketInventoryItem> findTicketInventoryItemByFestival(Festival festival);

}
