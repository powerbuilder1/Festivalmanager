package festivalmanager.stock;

import festivalmanager.catering.Food;
import org.salespointframework.inventory.UniqueInventory;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockManagment {

	private final UniqueInventory<UniqueInventoryItem> inventory;

	public StockManagment(UniqueInventory<UniqueInventoryItem> inventory) {
		this.inventory = inventory;
	}

	public Streamable<UniqueInventoryItem> getCurrentStock() {
		return inventory.findAll();
	}

	public void deleteAllInventoryItems(UniqueInventoryItem inventoryItem) {
		inventory.delete(inventoryItem);
	}

	public Optional<UniqueInventoryItem> findByProduct(Food foodItem) {
		return inventory.findByProduct(foodItem);
	}

	// Test
	public void deleteAll() {
		inventory.deleteAll();
	}
}
