package festivalmanager.catering;

import festivalmanager.stock.StockManagment;
import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;
import org.salespointframework.core.Currencies;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
public class CateringManagement {

	private final FoodCatalog foodCatalog;
	private final StockManagment stockManagment;

	public CateringManagement(FoodCatalog foodCatalog, StockManagment stockManagment) {
		this.foodCatalog = foodCatalog;
		this.stockManagment = stockManagment;
	}

	// add item to Catalog
	// TODO: fix because of foodItem need festival now
/*
	public void addItemToCatalog(NewFoodItemForm foodItemForm) {
		Food foodItem = foodCatalog.save(new Food(
				foodItemForm.getName(),
				Money.of(foodItemForm.getPrice(), Currencies.EURO),
				festival));
		stockManagment.initializeInventoryItem(foodItem, 0);
		for (Product p : foodCatalog.findAll()) {
			System.out.println(p.getName() + ": " + p.getPrice());
		}
	}
*/

	// get catalog
	public Streamable<Food> getCatalog() {
		return foodCatalog.findAll();
	}

	public void deleteItemFromCatalog(Food foodItem) {
		if (stockManagment.findByProduct(foodItem).isPresent()) {
			UniqueInventoryItem inventoryItem = stockManagment.findByProduct(foodItem).get();
			stockManagment.deleteAllInventoryItems(inventoryItem);
			foodCatalog.delete(foodItem);
		}
	}

	// update item form FoodCatalog
	public void editItemFromCatalog(Food foodItem, NewFoodItemForm foodItemForm) {
		if (foodItem.getId() != null) {
			foodItem.setName(foodItemForm.getName());
			foodItem.setPrice(Money.of(foodItemForm.getPrice(), Currencies.EURO));
			foodCatalog.save(foodItem);
		}
	}
}
