package festivalmanager.stock;

import festivalmanager.festival.Festival;
import org.salespointframework.inventory.UniqueInventory;
import org.springframework.data.util.Streamable;


public interface StockInventory extends UniqueInventory <FoodInventoryItem> {
	/**
	 * get FoodInventoryItems by Festival
	 * @param festival
	 * @return
	 */
	Streamable<FoodInventoryItem> findFoodInventoryItemsByFestival(Festival festival);
	void deleteFoodInventoryItemsByFestival_Id(Long festivalId);
}
