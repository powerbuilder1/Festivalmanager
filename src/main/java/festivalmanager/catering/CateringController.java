package festivalmanager.catering;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class CateringController {

	final private CateringManagement cateringManagement;

	public CateringController(CateringManagement cateringManagement) {
		this.cateringManagement = cateringManagement;
	}

	@PostMapping(path = "catering/addToFoodCatalog")
	public String addItemToCatalog(@Valid NewFoodItemForm foodItemForm, Errors errors) {
		cateringManagement.addItemToCatalog(foodItemForm);
		return "redirect:/";
	}


}
