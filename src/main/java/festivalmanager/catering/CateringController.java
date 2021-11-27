package festivalmanager.catering;

import org.salespointframework.catalog.ProductIdentifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class CateringController {

	final private CateringManagement cateringManagement;

	public CateringController(CateringManagement cateringManagement) {
		this.cateringManagement = cateringManagement;
	}

	// route to catering management page
	@GetMapping(path = "catering/management")
	public String getCateringManagement(Model model) {
		model.addAttribute("foodItemForm", new NewFoodItemForm());
		return "catering_management";
	}

	// route to catalog overview
	@GetMapping(path = "catering/catalog")
	public String getCatalog(Model model) {
		model.addAttribute("catalog", cateringManagement.getCatalog());
		return "catalog";
	}

	// route to edit page for catalog items
	@GetMapping(path = "catering/catalog/edit/{foodItem}")
	public String getEditCatalogItem(
			@PathVariable("foodItem") Food foodItem,
			Model model
			) {
		model.addAttribute("foodItem", foodItem);
		System.out.println(foodItem.getName());
		System.out.println(foodItem.getPrice());
		return "catalog_edit_item";
	}

	// add item to FoodCatalog
	@PostMapping(path = "catering/addToFoodCatalog")
	public String addItemToCatalog(@ModelAttribute NewFoodItemForm foodItemForm) {
		cateringManagement.addItemToCatalog(foodItemForm);
		return "redirect:/";
	}

	// delete item from FoodCatalog
	@PostMapping(path = "catering/deleteFromCatalog/{foodItem}")
	public String deleteItemFromCatalog(@PathVariable("foodItem") Food foodItem) {
		cateringManagement.deleteItemFromCatalog(foodItem);
		return "catalog";
	}

}
