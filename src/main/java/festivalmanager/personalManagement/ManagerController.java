package festivalmanager.personalManagement;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserForm;
import festivalmanager.authentication.UserManagement;
import festivalmanager.catering.Food;
import festivalmanager.catering.NewFoodItemForm;
import org.salespointframework.useraccount.QUserAccount;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
@PreAuthorize("hasRole('BOSS')")
public class ManagerController{
	public ManagerManagement managerManagement;

	public ManagerController(ManagerManagement managerManagement) {
		this.managerManagement = managerManagement;
	}

	@GetMapping("/dashboard")
	String dashboard(Model model) {
		return "dashboard";

	}

	@GetMapping("/team")
	String team(Model model) {
		model.addAttribute("userList", managerManagement.findAll());
		return "team";
	}


	@PostMapping("/new_personal")
	String registerNew(@Valid UserForm form, Errors result) {

		if (result.hasErrors()) {
			return "new_personal";
		}

		// (｡◕‿◕｡)
		// Falles alles in Ordnung ist legen wir einen Mitarbeiter an
		if(form.getPosition().equalsIgnoreCase("catering")){
			managerManagement.createCateringStaff(form);
		}else if (form.getPosition().equalsIgnoreCase("planning")){
			managerManagement.createPlanningStaff(form);
		}
		return "redirect:/";
	}

	@GetMapping("/new_personal")
	String register(Model model, UserForm form) {
		return "new_personal";
	}





}
