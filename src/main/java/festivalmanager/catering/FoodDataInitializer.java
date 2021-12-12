package festivalmanager.catering;

import festivalmanager.festival.FestivalManagement;
import org.javamoney.moneta.Money;
import org.salespointframework.core.Currencies;
import org.salespointframework.core.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(30)
public class FoodDataInitializer implements DataInitializer {

	private final FoodCatalog foodCatalog;
	private final FestivalManagement festivalManagement;

	public FoodDataInitializer(FoodCatalog foodCatalog, FestivalManagement festivalManagement) {
		this.foodCatalog = foodCatalog;
		this.festivalManagement = festivalManagement;
	}

	@Override
	public void initialize() {
		System.out.println("IS EMPTY:" + festivalManagement.findAllFestivals().isEmpty());
		if (!festivalManagement.findAllFestivals().isEmpty()) {
			foodCatalog.save(new Food("Bratwurst", Money.of(10, Currencies.EURO), festivalManagement.findAllFestivals().toList().get(0)));
			foodCatalog.save(new Food("Bier", Money.of(1, Currencies.EURO), festivalManagement.findAllFestivals().toList().get(1)));
			foodCatalog.save(new Food("Wiener", Money.of(3, Currencies.EURO), festivalManagement.findAllFestivals().toList().get(1)));
			foodCatalog.save(new Food("Cola", Money.of(1, Currencies.EURO), festivalManagement.findAllFestivals().toList().get(1)));
		}
	}
}
