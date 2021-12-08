package festivalmanager.Ticket;

import festivalmanager.Ticket.TicketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller

public class TicketController {
	TicketRepository ticketRepository;
	TicketController(TicketRepository ticketRepository) {
		Assert.notNull(ticketRepository, "ticketRepository must not be null");
		this.ticketRepository = ticketRepository;
	}

	@GetMapping("/ticket")

	String Tickets(Model model) {
		model.addAttribute("ticket", ticketRepository.findAll());
		model.addAttribute("title", "Tickets");
		return "ticket";
	}

}


