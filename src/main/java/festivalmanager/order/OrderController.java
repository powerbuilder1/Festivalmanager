package festivalmanager.order;

import festivalmanager.catering.CateringManagement;
import festivalmanager.stock.ReorderForm;
import org.salespointframework.order.Cart;
import org.salespointframework.order.Order;
import org.salespointframework.payment.Cash;
import org.salespointframework.quantity.Quantity;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

	@PostMapping(path = "catering/checkout")
	String buy(
			@ModelAttribute Cart cart,
			@LoggedIn Optional<UserAccount> userAccount
	) {
		return customOrderManagement.buy(cart, userAccount);
	}

}
