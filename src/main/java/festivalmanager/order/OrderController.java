package festivalmanager.order;

import festivalmanager.catering.CateringManagement;
import festivalmanager.stock.ReorderForm;
import org.salespointframework.order.Cart;
import org.salespointframework.quantity.Quantity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("isAuthenticated()")
@SessionAttributes("cart")
public class OrderController {

	private final CateringManagement cateringManagement;
	private final CustomOrderManagement customOrderManagement;

	public OrderController(CateringManagement cateringManagement, CustomOrderManagement customOrderManagement) {
		this.cateringManagement = cateringManagement;
		this.customOrderManagement = customOrderManagement;
	}


	@ModelAttribute("cart")
	Cart initializeCart() {
		return new Cart();
	}

	@GetMapping(path = "catering/sale")
	String getCart(Model model) {
		model.addAttribute("catalog", cateringManagement.getCatalog());
		model.addAttribute("orderForm", new ReorderForm());
		return "catering";
	}

	@PostMapping(path = "catering/order")
	String addFoodToCard(
			@ModelAttribute Cart cart,
			@ModelAttribute ReorderForm orderForm
			) {

		customOrderManagement.addFoodToCard(cart, orderForm);

		System.out.println(cart.isEmpty());
		return "redirect:/catering/sale";
	}

}
