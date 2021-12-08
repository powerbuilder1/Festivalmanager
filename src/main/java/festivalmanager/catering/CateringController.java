package festivalmanager.catering;

import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class CateringController {

	final private CateringManagement cateringManagement;

	public CateringController(CateringManagement cateringManagement) {
		this.cateringManagement = cateringManagement;
	}

	// route to catering management page
	@PreAuthorize("hasRole('FESTIVALDIRECTOR')")
	@GetMapping(path = "catering/management")
	public String getCateringManagement(Model model) {
		model.addAttribute("foodItemForm", new NewFoodItemForm());
		return "catering_management";
	}

	// route to catalog overview
	@PreAuthorize("hasRole('FESTIVALDIRECTOR')")
	@GetMapping(path = "catering/catalog")
	public String getCatalog(
			Model model,
			@LoggedIn Optional<UserAccount> userAccount
	) {
		model.addAttribute("catalog", cateringManagement.getCatalog(userAccount));
		return "catalog";
	}

	// route to edit page for catalog items
	@PreAuthorize("hasRole('FESTIVALDIRECTOR')")
	@GetMapping(path = "catering/catalog/edit/{foodItem}")
	public String getEditCatalogItem(
			@PathVariable("foodItem") Food foodItem,
			Model model
			) {
		model.addAttribute("item", foodItem);
		model.addAttribute("foodItemForm", new NewFoodItemForm());
		System.out.println(foodItem.getName());
		System.out.println(foodItem.getPrice());
		return "catalog_edit_item";
	}

	// add item to FoodCatalog
	@PreAuthorize("hasRole('FESTIVALDIRECTOR')")
	@PostMapping(path = "catering/addToFoodCatalog")
	public String addItemToCatalog(
			@ModelAttribute NewFoodItemForm foodItemForm,
			@LoggedIn Optional<UserAccount> account
	) {
		cateringManagement.addItemToCatalog(foodItemForm, account);
		return "redirect:/";
	}

	// delete item from FoodCatalog
	@PreAuthorize("hasRole('FESTIVALDIRECTOR')")
	@PostMapping(path = "catering/deleteFromCatalog/{foodItem}")
	public String deleteItemFromCatalog(@PathVariable("foodItem") Food foodItem) {
		cateringManagement.deleteItemFromCatalog(foodItem);
		return "redirect:/catering/catalog";
	}

	// edit item from FoodCatalog
	@PreAuthorize("hasRole('FESTIVALDIRECTOR')")
	@PostMapping(path = "catering/editFromCatalog/{foodItem}")
	public String editItemFromCatalog(
			@PathVariable("foodItem") Food foodItem,
			@ModelAttribute NewFoodItemForm foodItemForm
	) {
		cateringManagement.editItemFromCatalog(foodItem, foodItemForm);
		return "redirect:/catering/catalog";
	}

	@GetMapping(path = "catering/test")
	public String test(
			Model model,
			@LoggedIn Optional<UserAccount> userAccount
	) {
		model.addAttribute("test", "LOL");
		userAccount.ifPresent(account -> {
			model.addAttribute("userAccount", account);
		});
		return "test";
	}

}
