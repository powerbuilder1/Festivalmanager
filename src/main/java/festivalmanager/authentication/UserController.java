package festivalmanager.authentication;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
	private final UserManagement userManagement;

	UserController(UserManagement userManagement) {
		Assert.notNull(userManagement, "UserManagement must not be null!");
		this.userManagement = userManagement;
	}
}