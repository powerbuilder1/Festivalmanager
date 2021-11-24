package festivalmanager.catering;

import org.javamoney.moneta.Money;
import org.salespointframework.core.Currencies;
import org.springframework.stereotype.Service;

@Service
public class CateringManagement {

	private final FoodCatalog foodCatalog;

	public CateringManagement(FoodCatalog foodCatalog) {
		this.foodCatalog = foodCatalog;
	}

	public void addItemToCatalog(NewFoodItemForm foodItemForm) {
		foodCatalog.save(new Food(
				foodItemForm.getName(),
				Money.of(foodItemForm.getPrice(), Currencies.EURO)
		));
	}
}
