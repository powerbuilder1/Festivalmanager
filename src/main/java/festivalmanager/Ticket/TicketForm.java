package festivalmanager.Ticket;

import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

public class TicketForm {
	  private String name;
	  private double price;
	  private Festival festival;
	  private Ticket.TicketType ticketType;
		@Range(min=0, max=100)
	  private int percentOf;
		private long festivalIdentifier;


	public TicketForm(String name, Festival festival, double price, Ticket.TicketType ticketType, int percentOf) {
		this.name = name;
		this.price = price;
		this.festival = festival;
		this.ticketType = ticketType;
		this.percentOf = percentOf;
	}
	public TicketForm() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public Festival getFestival() {
		return festival;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Ticket.TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(Ticket.TicketType ticketType) {
		this.ticketType = ticketType;
	}

	public int getPercentOf() {
		return percentOf;
	}

	public long getFestivalIdentifier() {
		return festivalIdentifier;
	}

	public void setFestivalIdentifier(long festivalIdentifier) {
		this.festivalIdentifier = festivalIdentifier;
	}

	public void setPercentOf(int percentOf) {
		this.percentOf = percentOf;
	}


}
