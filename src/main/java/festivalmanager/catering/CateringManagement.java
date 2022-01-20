package festivalmanager.catering;

import festivalmanager.authentication.UserManagement;
import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.stock.FoodInventoryItem;
import festivalmanager.stock.StockManagment;
import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;
import org.salespointframework.catalog.ProductIdentifier;
import org.salespointframework.core.Currencies;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.useraccount.UserAccount;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CateringManagement {

	private final FoodCatalog foodCatalog;
	private final StockManagment stockManagment;
	private FestivalManagement festivalManagement;
	private final UserManagement userManagement;

	public CateringManagement(FoodCatalog foodCatalog, StockManagment stockManagment, UserManagement userManagement) {
		this.foodCatalog = foodCatalog;
		this.stockManagment = stockManagment;
		this.userManagement = userManagement;
		this.festivalManagement = null;
	}

	public void setFestivalManagement(FestivalManagement festivalManagement) {
		this.festivalManagement = festivalManagement;
	}

	// add item to Catalog
	public void addItemToCatalog(NewFoodItemForm foodItemForm, Optional<UserAccount> userAccount) {
		userAccount.ifPresent(account -> {
			Festival festival = userManagement.findUserByUserAccount(userAccount.get()).getFestival();
			Food foodItem = foodCatalog.save(new Food(
					foodItemForm.getName(),
					Money.of(foodItemForm.getPrice(), Currencies.EURO),
					festival));
			stockManagment.initializeInventoryItem(foodItem, 0, festival);
		});
		// get festival from user over userAccount
	}

	// get catalog
	public Streamable<Food> getCatalog(Optional<UserAccount> userAccount) {
		if (userAccount.isPresent()) {
			Festival festival = userManagement.findUserByUserAccount(userAccount.get()).getFestival();
			return foodCatalog.findFoodsByFestival(festival);
		}
		return null;
	}

	public Streamable<Food> getCatalog(long festivalId) {
		Festival festival = festivalManagement.findById(festivalId);
		return foodCatalog.findFoodsByFestival(festival);
	}

	public void deleteItemFromCatalog(Food foodItem) {
		if (stockManagment.findByProduct(foodItem).isPresent()) {
			FoodInventoryItem inventoryItem = stockManagment.findByProduct(foodItem).get();
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

	public Optional<Food> findProductById(ProductIdentifier productIdentifier) {
		return foodCatalog.findById(productIdentifier);
	}
}
