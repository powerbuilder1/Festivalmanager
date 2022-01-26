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


	/**
	 * constructor
	 * @param name
	 * @param price
	 * @param ticketType
	 * @param festival
	 * @param percentOf

	 */

	public TicketForm(String name, Festival festival, double price, Ticket.TicketType ticketType, int percentOf) {
		this.name = name;
		this.price = price;
		this.festival = festival;
		this.ticketType = ticketType;
		this.percentOf = percentOf;
	}
	/**
	 * default constructor
	 */
	public TicketForm() {

	}
	/**
	 * getter for name
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * setter
	 *
	 * @param name to set the name {@link String}
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getter for name
	 * @return
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * getter for name
	 * @return
	 */
	public Festival getFestival() {
		return festival;
	}
	/**
	 * setter
	 *
	 * @param festival to set the festival {@link Festival}
	 */
	public void setFestival(Festival festival) {
		this.festival = festival;
	}
	/**
	 * setter
	 *
	 * @param price to set the price {@link Double}
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * getter for ticket type
	 * @return
	 */
	public Ticket.TicketType getTicketType() {
		return ticketType;
	}
	/**
	 * setter
	 *
	 * @param ticketType to set the ticket type {@link Ticket.TicketType}
	 */
	public void setTicketType(Ticket.TicketType ticketType) {
		this.ticketType = ticketType;
	}
	/**
	 * getter for percent of tickets
	 * @return
	 */
	public int getPercentOf() {
		return percentOf;
	}
	/**
	 * getter for festival id
	 * @return
	 */
	public long getFestivalIdentifier() {
		return festivalIdentifier;
	}
	/**
	 * setter
	 *
	 * @param festivalIdentifier to set the festival id {@link Long}
	 */
	public void setFestivalIdentifier(long festivalIdentifier) {
		this.festivalIdentifier = festivalIdentifier;
	}
	/**
	 * setter
	 *
	 * @param percentOf to set the percentage of a type of ticket for a festival {@link Integer}
	 */
	public void setPercentOf(int percentOf) {
		this.percentOf = percentOf;
	}


}
