package festivalmanager.order;

import festivalmanager.catering.CateringManagement;
import festivalmanager.catering.Food;
import org.salespointframework.order.Cart;
import org.salespointframework.order.Order;
import org.salespointframework.order.OrderManagement;
import org.salespointframework.quantity.Quantity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("isAuthenticated()")
@SessionAttributes("cart")
public class OrderController {

	private final OrderManagement<Order> orderOrderManagement;
	private final CateringManagement cateringManagement;

	public OrderController(OrderManagement<Order> orderOrderManagement, CateringManagement cateringManagement) {
		this.orderOrderManagement = orderOrderManagement;
		this.cateringManagement = cateringManagement;
	}


	@ModelAttribute("cart")
	Cart initializeCart() {
		return new Cart();
	}

	@GetMapping(path = "catering/order")
	String getCart(Model model) {
		model.addAttribute("catalog", cateringManagement.getCatalog());
		return "catering";
	}

	@PostMapping(path = "catering/order")
	String addFoodToCard(
			@RequestParam("foodItemId") Food food,
			@RequestParam("amount") int amount,
			@ModelAttribute Cart cart
			) {

		// add food items to card
		cart.addOrUpdateItem(food, Quantity.of(amount));
		return "redirect:/catering/order";
	}

}
