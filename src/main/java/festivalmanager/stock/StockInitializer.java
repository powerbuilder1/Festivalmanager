package festivalmanager.stock;

import festivalmanager.festival.FestivalManagement;
import org.salespointframework.core.DataInitializer;
import org.salespointframework.inventory.UniqueInventory;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.quantity.Quantity;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import festivalmanager.catering.FoodCatalog;

@Component
@Order(30)
public class StockInitializer implements DataInitializer {

	private final UniqueInventory<UniqueInventoryItem> inventory;
	private final FoodCatalog foodCatalog;
	private final FestivalManagement festivalManagement;

	public StockInitializer(UniqueInventory<UniqueInventoryItem> inventory, FoodCatalog foodCatalog, FestivalManagement festivalManagement) {
		this.inventory = inventory;
		this.foodCatalog = foodCatalog;
		this.festivalManagement = festivalManagement;
	}

	@Override
	public void initialize() {
		foodCatalog.findAll().forEach(food -> {
			if (inventory.findByProduct(food).isEmpty() && !festivalManagement.findAllFestivals().isEmpty()) {
				inventory.save(new FoodInventoryItem(food, Quantity.of(5), festivalManagement.findAllFestivals()
						.toList().get(0)));
			}
		});
	}
}
