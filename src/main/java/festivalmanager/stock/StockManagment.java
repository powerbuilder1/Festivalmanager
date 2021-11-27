package festivalmanager.stock;

import festivalmanager.catering.Food;
import org.salespointframework.inventory.UniqueInventory;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.quantity.Quantity;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockManagment {

	private final UniqueInventory<UniqueInventoryItem> inventory;

	public StockManagment(UniqueInventory<UniqueInventoryItem> inventory) {
		this.inventory = inventory;
	}

	// get current stock
	public Streamable<UniqueInventoryItem> getCurrentStock() {
		return inventory.findAll();
	}

	// delete all inventory items for a specific FoodItem
	public void deleteAllInventoryItems(UniqueInventoryItem inventoryItem) {
		inventory.delete(inventoryItem);
	}

	// find a UniqueInventoryItem by Food-Product
	public Optional<UniqueInventoryItem> findByProduct(Food foodItem) {
		return inventory.findByProduct(foodItem);
	}

	// initialize new Inventory Item for a specific Food-Product
	public void initializeInventoryItem(Food foodItem, double amount) {
		inventory.save(new UniqueInventoryItem(foodItem, Quantity.of(amount)));
	}



	// Test
	public void deleteAll() {
		inventory.deleteAll();
	}
}
