package festivalmanager.TicketStock;

import festivalmanager.festival.Festival;
import org.salespointframework.inventory.UniqueInventory;
import org.springframework.data.util.Streamable;


public interface TicketStockInventory extends UniqueInventory <TicketInventoryItem> {
	Streamable<TicketInventoryItem> findTicketInventoryItemByFestival(Festival festival);

}
