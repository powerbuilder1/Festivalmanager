package festivalmanager.Ticket;

import festivalmanager.festival.Festival;
import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;
import org.salespointframework.catalog.ProductIdentifier;

import javax.persistence.*;

@Entity
public class Ticket extends Product {

	public static enum TicketType {
		AbendKasse, CampingTicket, TagesKarte;
	}

	@ManyToOne
	@JoinColumn(name = "festival_id", nullable = false)
	private Festival festival;
	private TicketType type;
	int count;
	private double percentOf;


	public Ticket(String name, Money price,TicketType type, Festival festival, double percentOf ) {
		super(name, price);
		this.setType(type);
		this.setFestival(festival);
		this.percentOf = percentOf;
		this.setCount((int) (festival.getLocation().getMaxVisitors()* (this.percentOf/100)));

	}



	@SuppressWarnings({ "unused", "deprecation" })
	public Ticket() {}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Festival getFestival() {
		return festival;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
	}

	public double getPercentOf() {
		return percentOf;
	}

	public void setPercentOf(double percentOf) {
		this.percentOf = percentOf;
	}

	public TicketType getType() {
		return type;
	}

	public void setType(TicketType type) {
		this.type = type;
	}
}
