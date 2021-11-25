package festivalmanager.catering;

import festivalmanager.stock.StockManagment;
import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;
import org.salespointframework.core.Currencies;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CateringManagement {

	private final FoodCatalog foodCatalog;
	private final StockManagment stockManagment;

	public CateringManagement(FoodCatalog foodCatalog, StockManagment stockManagment) {
		this.foodCatalog = foodCatalog;
		this.stockManagment = stockManagment;
	}

	public void addItemToCatalog(NewFoodItemForm foodItemForm) {
		foodCatalog.save(new Food(
				foodItemForm.getName(),
				Money.of(foodItemForm.getPrice(), Currencies.EURO)
		));
		for (Product p : foodCatalog.findAll()) {
			System.out.println(p.getName() + ": " + p.getPrice());
		}
	}

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
}
