package festivalmanager.TicketStock;

import org.salespointframework.catalog.ProductIdentifier;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class TicketOrderForm implements Serializable {
	private @NotEmpty ProductIdentifier ticketItemId;
	private @NotEmpty int amount;

	public TicketOrderForm (ProductIdentifier ticketItemId, int amount) {
		this.ticketItemId = ticketItemId;
		this.amount = amount;
	}

	public TicketOrderForm() {
	}

	public ProductIdentifier getTicketItemId() {
		return ticketItemId;
	}

	public void setTicketItemId(ProductIdentifier ticketItemId) {
		this.ticketItemId = ticketItemId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void validate(Errors errors) {
		// Complex validation goes here
	}
}
