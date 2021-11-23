package kickstart.stock;

import org.salespointframework.inventory.UniqueInventory;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
public class StockManagment {

	private final UniqueInventory<UniqueInventoryItem> inventory;

	public StockManagment(UniqueInventory<UniqueInventoryItem> inventory) {
		this.inventory = inventory;
	}

	public Streamable<UniqueInventoryItem> getCurrentStock() {
		return inventory.findAll();
	}
}
