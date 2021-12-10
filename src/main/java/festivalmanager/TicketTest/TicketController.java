package festivalmanager.TicketTest;

import org.salespointframework.order.Cart;
import org.salespointframework.order.Order;
import org.salespointframework.order.OrderManagement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TicketController  {
	TicketRepository ticketRepository;
	private final OrderManagement<Order> orderManagement;

	public TicketController(OrderManagement<Order> orderManagement, TicketRepository ticketRepository) {
		Assert.notNull(orderManagement, "OrderManagement must not be null!");
		this.orderManagement = orderManagement;
		this.ticketRepository = ticketRepository;
	}
	@GetMapping("/ticket")

	String Tickets(Model model) {
		model.addAttribute("ticket", ticketRepository.findAll());
		model.addAttribute("title", "Tickets");
		return "ticket";
	}

	@ModelAttribute("cart")
	Cart initializeCart() {
		return new Cart();
	}


}
