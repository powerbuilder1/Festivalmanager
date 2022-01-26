package festivalmanager.Ticket;

import festivalmanager.festival.Festival;
import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Ticket extends Product {

	/**
	 * enum
	 *
	 * the ticket types that can be found in a festival

	 */
	public static enum TicketType {
		ABENDKASSE, CAMPINGTICKET, TAGESKARTE;
	}

	@ManyToOne
	@JoinColumn(name = "festival_id", nullable = false)
	private Festival festival;
	private TicketType type;
	int count;
	private double percentOf;

	/**
	 * constructor
	 * @param name
	 * @param price
	 * @param type
	 * @param festival
	 * @param percentOf

	 */

	public Ticket(String name, Money price,TicketType type, Festival festival, double percentOf ) {
		super(name, price);
		this.setType(type);
		this.setFestival(festival);
		this.percentOf = percentOf;
		this.setCount((int) (festival.getLocation().getMaxVisitors()* (this.percentOf/100)));

	}



	@SuppressWarnings({ "unused", "deprecation" })
	public Ticket() {}

	/**
	 * getter for the count of a type of ticket in a festival
	 * @return
	 */
	public int getCount() {
		return count;
	}
	/**
	 * setter for the count of a type of ticket in a festival
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
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
	/**
	 * getter for the percent of a type of ticket in all the amount of tickets in a festival
	 * @return
	 */
	public double getPercentOf() {
		return percentOf;
	}
	/**
	 * setter for percent of
	 * @param percentOf
	 */
	public void setPercentOf(double percentOf) {
		this.percentOf = percentOf;
	}
	/**
	 * getter for type of ticket
	 * @return
	 */
	public TicketType getType() {
		return type;
	}
	/**
	 * setter for type of ticket
	 * @param type
	 */
	public void setType(TicketType type) {
		this.type = type;
	}
}
