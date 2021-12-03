package festivalmanager.order;

import org.salespointframework.order.Cart;
import org.salespointframework.order.Order;
import org.salespointframework.order.OrderManagement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@PreAuthorize("isAuthenticated()")
@SessionAttributes("cart")
public class OrderController {

	private final OrderManagement<Order> orderOrderManagement;

	public OrderController(OrderManagement<Order> orderOrderManagement) {
		this.orderOrderManagement = orderOrderManagement;
	}


	@ModelAttribute("cart")
	Cart initializeCart() {
		return new Cart();
	}

}
