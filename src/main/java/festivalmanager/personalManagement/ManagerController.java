package festivalmanager.personalManagement;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ManagerController {
	public ManagerManagement managerManagement;

	public ManagerController(ManagerManagement managerManagement) {
		this.managerManagement = managerManagement;
	}

	@GetMapping("/dashboard")
	@PreAuthorize("hasRole('BOSS')")
	String dashboard(Model model) {
		return "dashboard";

	}

}
