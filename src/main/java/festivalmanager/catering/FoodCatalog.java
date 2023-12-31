package festivalmanager.catering;

import festivalmanager.festival.Festival;
import org.salespointframework.catalog.Catalog;
import org.springframework.data.util.Streamable;

public interface FoodCatalog extends Catalog<Food> {
	/**
	 *
	 * @param festival
	 * @return
	 */
	Streamable<Food> findFoodsByFestival(Festival festival);
	void deleteFoodsByFestival_Id(Long festivalId);
}
