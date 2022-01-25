package festivalmanager.stock;

import festivalmanager.festival.Festival;
import org.salespointframework.catalog.Product;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.quantity.Quantity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FoodInventoryItem extends UniqueInventoryItem {


	@ManyToOne
	@JoinColumn(name = "festival_id", nullable = false)
	private Festival festival;

	/**
	 *
	 * @param product
	 * @param quantity
	 * @param festival
	 */
	public FoodInventoryItem(Product product, Quantity quantity, Festival festival) {
		super(product, quantity);
		this.festival = festival;
	}

	public FoodInventoryItem() {
	}

	public Festival getFestival() {
		return festival;
	}

	public long getFestivalId() {
		return festival.getId();
	}
}
