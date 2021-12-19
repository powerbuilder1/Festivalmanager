package festivalmanager.stock;

import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class StockController {

	final private StockManagment stockManagment;


	public StockController(StockManagment stockManagment) {
		this.stockManagment = stockManagment;
	}


	// route to stock overview
	@PreAuthorize("hasRole('FESTIVALDIRECTOR')")
	@GetMapping(path = "stock")
	public String getCurrentStock(
			ReorderForm form,
			Model model,
			@LoggedIn Optional<UserAccount> userAccount
			) {
		// add current stock to model
		model.addAttribute("stock", stockManagment.getCurrentStock(userAccount));
		return "stock";
	}

	// delete all inventory Items for a specific FoodItem
	@PreAuthorize("hasRole('FESTIVALDIRECTOR')")
	@PostMapping(path = "stock/deleteAllInventoryItems/{inventoryItem}")
	public String deleteAllInventoryItems(@PathVariable FoodInventoryItem inventoryItem) {
		stockManagment.deleteAllInventoryItems(inventoryItem);
		return "stock";
	}

	@PreAuthorize("hasRole('FESTIVALDIRECTOR')")
	@PostMapping(path = "stock/reorder")
	public String reorderItem(
			@Valid ReorderForm form,
			BindingResult result,
			@LoggedIn Optional<UserAccount> userAccount,
			Model model
	) {
		if (result.hasErrors()) {
			model.addAttribute("stock", stockManagment.getCurrentStock(userAccount));
			return "stock";
		}
		stockManagment.reorderItem(form);
		return "redirect:/stock/";
	}


	 //TEST
	@PostMapping(path = "stock/test/deleteAll")
	public String deleteAll() {
		stockManagment.deleteAll();
		return "stock";
	}

}
