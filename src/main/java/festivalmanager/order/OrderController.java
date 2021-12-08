package festivalmanager.order;

import festivalmanager.authentication.User;
import festivalmanager.catering.CateringManagement;
import festivalmanager.stock.ReorderForm;
import groovy.util.logging.Log;
import org.salespointframework.order.Cart;
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

	@PreAuthorize("hasRole('CATERING')")
	@GetMapping(path = "catering/sale")
	String getCart(
			Model model,
			@LoggedIn Optional<UserAccount> userAccount
			) {
		model.addAttribute("catalog", cateringManagement.getCatalog(userAccount));
		model.addAttribute("orderForm", new ReorderForm());
		return "catering";
	}

	@PreAuthorize("hasRole('CATERING')")
	@PostMapping(path = "catering/order")
	String addFoodToCard(
			@ModelAttribute Cart cart,
			@ModelAttribute ReorderForm orderForm,
			@LoggedIn Optional<UserAccount> userAccount
			) {

		customOrderManagement.addFoodToCard(cart, orderForm);

		return "redirect:/catering/sale";
	}

	@PreAuthorize("hasRole('CATERING')")
	@PostMapping(path = "catering/checkout")
	String buy(
			@ModelAttribute Cart cart,
			@LoggedIn Optional<UserAccount> userAccount
	) {
		return customOrderManagement.buy(cart, userAccount);
	}

}
