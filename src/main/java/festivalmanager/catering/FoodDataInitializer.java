package festivalmanager.catering;

import org.javamoney.moneta.Money;
import org.salespointframework.core.Currencies;
import org.salespointframework.core.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(20)
public class FoodDataInitializer implements DataInitializer {

	private final FoodCatalog foodCatalog;

	public FoodDataInitializer(FoodCatalog foodCatalog) {
		this.foodCatalog = foodCatalog;
	}

	@Override
	public void initialize() {
		foodCatalog.save(new Food("Bratwurst", Money.of(10, Currencies.EURO)));
		foodCatalog.save(new Food("Bier", Money.of(1, Currencies.EURO)));
	}
}
