package festivalmanager.stock;

import org.salespointframework.core.DataInitializer;
import org.salespointframework.inventory.UniqueInventory;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.quantity.Quantity;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import festivalmanager.catering.FoodCatalog;

@Component
@Order(20)
public class StockInitializer implements DataInitializer {

	private final UniqueInventory<UniqueInventoryItem> inventory;
	private final FoodCatalog foodCatalog;

	public StockInitializer(UniqueInventory<UniqueInventoryItem> inventory, FoodCatalog foodCatalog) {
		this.inventory = inventory;
		this.foodCatalog = foodCatalog;
	}

	@Override
	public void initialize() {
		foodCatalog.findAll().forEach(food -> {
			if (inventory.findByProduct(food).isEmpty()) {
				inventory.save(new UniqueInventoryItem(food, Quantity.of(5)));
			}
		});
	}
}
