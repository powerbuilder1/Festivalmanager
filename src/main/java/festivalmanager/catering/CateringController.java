package festivalmanager.catering;

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
public class CateringController {

	final private CateringManagement cateringManagement;

	public CateringController(CateringManagement cateringManagement) {
		this.cateringManagement = cateringManagement;
	}

	// route to catering management page
	@PreAuthorize("hasRole('FESTIVALDIRECTOR')")
	@GetMapping(path = "catering/addToFoodCatalog")
	public String getCateringManagement(NewFoodItemForm form) {
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
			NewFoodItemForm form,
			@PathVariable("foodItem") Food foodItem,
			Model model
			) {
		model.addAttribute("item", foodItem);
		System.out.println(foodItem.getName());
		System.out.println(foodItem.getPrice());
		return "catalog_edit_item";
	}

	// add item to FoodCatalog
	@PreAuthorize("hasRole('FESTIVALDIRECTOR')")
	@PostMapping(path = "catering/addToFoodCatalog")
	public String addItemToCatalog(
			@LoggedIn Optional<UserAccount> account,
			@Valid NewFoodItemForm form,
			BindingResult result
	) {
		if (result.hasErrors()) {
			return "catering_management";
		}
		cateringManagement.addItemToCatalog(form, account);
		return "redirect:/catering/catalog";
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
			@Valid NewFoodItemForm form,
			BindingResult result
	) {
		if (result.hasErrors()) {
			return "redirect:/catering/catalog/edit/" + foodItem.getId();
		}
		cateringManagement.editItemFromCatalog(foodItem, form);
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
