package festivalmanager.stock;

import festivalmanager.catering.Food;
import festivalmanager.festival.Festival;
import org.salespointframework.catalog.ProductIdentifier;
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
	public void initializeInventoryItem(Food foodItem, double amount, Festival festival) {
		inventory.save(new FoodInventoryItem(foodItem, Quantity.of(amount), festival));
	}



	// Test
	public void deleteAll() {
		inventory.deleteAll();
	}

	public void reorderItem(ReorderForm reorderForm) {
		ProductIdentifier foodItemId = reorderForm.getFoodItemId();
		inventory.findByProductIdentifier(foodItemId).ifPresent(uniqueInventoryItem -> {
			uniqueInventoryItem.increaseQuantity(Quantity.of(reorderForm.getAmount()));
			inventory.save(uniqueInventoryItem);
		});

/*
		Optional<UniqueInventoryItem> foodInventoryItem = inventory.findByProductIdentifier(foodItemId);
		foodInventoryItem.ifPresent(uniqueInventoryItem
				-> uniqueInventoryItem.increaseQuantity(Quantity.of(reorderForm.getAmount())));
*/
	}
}
