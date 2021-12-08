package festivalmanager.stock;

import groovy.util.logging.Log;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.html.Option;
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
			Model model,
			@LoggedIn Optional<UserAccount> userAccount
			) {
		// add current stock to model
		model.addAttribute("stock", stockManagment.getCurrentStock(userAccount));
		// add reorderForm container to model
		model.addAttribute("reorderForm", new ReorderForm());
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
	public String reorderItem(@ModelAttribute ReorderForm reorderForm) {
		stockManagment.reorderItem(reorderForm);
		return "redirect:/stock";
	}


	 //TEST
	@PostMapping(path = "stock/test/deleteAll")
	public String deleteAll() {
		stockManagment.deleteAll();
		return "stock";
	}

}
