package festivalmanager.TicketStock;

import festivalmanager.festival.Festival;
import org.salespointframework.catalog.Product;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.quantity.Quantity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TicketInventoryItem extends UniqueInventoryItem {


	@ManyToOne
	@JoinColumn(name = "festival_id", nullable = false)
	private Festival festival;

	/**
	 * Constructor
	 *
	 * @param product
	 * @param quantity
	 * @param festival
	 */
	public TicketInventoryItem(Product product,Quantity quantity, Festival festival) {
		super(product, quantity);
		this.festival = festival;
	}
	/**
	 * default constructor
	 */
	public TicketInventoryItem() {

	}
	/**
	 * getter for festival
	 * @return
	 */
	public Festival getFestival() {
		return festival;
	}

	/**
	 * setter for festival
	 * @param festival
	 */
	public void setFestival(Festival festival) {
		this.festival = festival;
	}


}

