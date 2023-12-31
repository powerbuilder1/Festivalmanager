package festivalmanager.catering;

import festivalmanager.festival.Festival;
import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Food extends Product {

	@ManyToOne
	@JoinColumn(name = "festival_id", nullable = false)
	private Festival festival;

	/**
	 *
	 * @param name
	 * @param price
	 * @param festival
	 */
	public Food(String name, Money price, Festival festival) {
		super(name, price);
		this.festival = festival;
	}

	public Food() {
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
