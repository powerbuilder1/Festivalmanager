package festivalmanager.order;

import festivalmanager.TicketStock.TicketOrderForm;
import festivalmanager.Ticket.TicketRepository;
import org.salespointframework.order.Cart;
import org.salespointframework.order.Order;
import org.salespointframework.order.OrderManagement;
import org.salespointframework.payment.Cash;
import org.salespointframework.quantity.Quantity;
import org.salespointframework.useraccount.UserAccount;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketCustomOrderManagement {

	private final OrderManagement<Order> orderOrderManagement;
	private final TicketRepository ticketRepository;

	public TicketCustomOrderManagement(OrderManagement<Order> orderOrderManagement, TicketRepository ticketRepository) {
		this.orderOrderManagement = orderOrderManagement;
		this.ticketRepository = ticketRepository;

	}

	public void addTicketToCart(Cart cart, TicketOrderForm ticketOrderForm) {
		// find ticket product by ID
		System.out.println(ticketOrderForm.getTicketItemId());
		System.out.println(ticketOrderForm.getAmount());
		ticketRepository.findById(ticketOrderForm.getTicketItemId()).ifPresent(ticket -> {
			// add ticket items to card
			cart.addOrUpdateItem(ticket, Quantity.of(ticketOrderForm.getAmount()));
		});
	}
	public void deleteAllTicketsToCart(Cart cart) {
		// find ticket product by ID
			cart.clear();
		}


		public String buyTicket(Cart cart, Optional<UserAccount> userAccount) {
		return userAccount.map(account -> {
			Order order = new Order(account, Cash.CASH);
			cart.addItemsTo(order);
			orderOrderManagement.payOrder(order);
			orderOrderManagement.completeOrder(order);

			return "redirect:/pdf";
		}).orElse("redirect:/");
	}
}

