package festivalmanager.stock;

import festivalmanager.festival.Festival;
import org.salespointframework.inventory.UniqueInventory;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.springframework.data.util.Streamable;

public interface StockInventory extends UniqueInventory<FoodInventoryItem> {
	Streamable<FoodInventoryItem> findFoodInventoryItemsByFestival(Festival festival);
}
