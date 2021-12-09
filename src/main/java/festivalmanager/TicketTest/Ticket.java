package festivalmanager.TicketTest;

import festivalmanager.festival.Festival;
import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;

import javax.money.MonetaryAmount;
import javax.persistence.Entity;

@Entity
public class Ticket extends Product {



	public static enum TicketType {
		AbendKasse, CampingTicket, TagesKarte;
	}

	private TicketType type;
	private Long festivalId;



	public Ticket(String name, Money price, Long festivalId, TicketType type) {
		super(name, price);
		this.festivalId = festivalId;
		this.type = type;

	}

	@SuppressWarnings({ "unused", "deprecation" })
	private Ticket() {}

	public TicketType getType() {
		return type;
	}


}
