package festivalmanager.catering;

import festivalmanager.festival.Festival;
import org.salespointframework.catalog.Catalog;
import org.salespointframework.catalog.ProductIdentifier;
import org.springframework.data.util.Streamable;

public interface FoodCatalog extends Catalog<Food> {
	Streamable<Food> findFoodsByFestival(Festival festival);
}
