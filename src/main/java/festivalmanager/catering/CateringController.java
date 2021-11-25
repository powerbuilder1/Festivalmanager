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

	@GetMapping(path = "catering/management")
	public String getCateringManagement(Model model) {
		model.addAttribute("foodItemForm", new NewFoodItemForm());
		return "catering_management";
	}

	@PostMapping(path = "catering/addToFoodCatalog")
	public String addItemToCatalog(@ModelAttribute NewFoodItemForm foodItemForm) {
		cateringManagement.addItemToCatalog(foodItemForm);
		return "redirect:/";
	}

	@GetMapping(path = "catering/catalog")
	public String getCatalog(Model model) {
		model.addAttribute("catalog", cateringManagement.getCatalog());
		return "catalog";
	}

	@PostMapping(path = "catering/deleteFromCatalog/{foodItem}")
	public String deleteItemFromCatalog(@PathVariable("foodItem") Food foodItem) {
		cateringManagement.deleteItemFromCatalog(foodItem);
		return "catalog";
	}

}
