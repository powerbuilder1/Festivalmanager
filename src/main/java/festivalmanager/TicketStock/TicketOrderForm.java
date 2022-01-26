package festivalmanager.TicketStock;

import org.salespointframework.catalog.ProductIdentifier;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class TicketOrderForm implements Serializable {
	private @NotEmpty ProductIdentifier ticketItemId;
	private @NotEmpty int amount;
	/**
	 * Constructor
	 * @param ticketItemId
	 * @param amount
	 */
	public TicketOrderForm (ProductIdentifier ticketItemId, int amount) {
		this.ticketItemId = ticketItemId;
		this.amount = amount;
	}
	/**
	 * default constructor
	 */
	public TicketOrderForm() {
	}
	/**
	 * getter for id of the ticket item
	 * @return
	 */
	public ProductIdentifier getTicketItemId() {
		return ticketItemId;
	}
	/**
	 * setter for id of ticket item
	 * @param ticketItemId
	 */
	public void setTicketItemId(ProductIdentifier ticketItemId) {
		this.ticketItemId = ticketItemId;
	}
	/**
	 * getter for amount
	 * @return
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * setter for amount
	 * @param amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}


}
