package festivalmanager.TicketStock;

import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;


@Controller
public class TicketStockController {

	final private TicketStockManagement ticketStockManagement;


	public TicketStockController(TicketStockManagement ticketStockManagement) {
		this.ticketStockManagement = ticketStockManagement;
	}

	// route to stock overview
	@PreAuthorize("hasRole('FESTIVALDIRECTOR')")
	@GetMapping(path = "/ticketstock")
	public String getCurrentTicketStock(
			Model model,
			@LoggedIn Optional<UserAccount> userAccount
	) {
		// add current stock to model
		model.addAttribute("stock", ticketStockManagement.getCurrentTicketStock(userAccount));
		return "ticketstock";
	}
}
