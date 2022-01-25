package festivalmanager.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

@Controller
public class UserController {
	private final UserManagement userManagement;

	/**
	 * Creates a new {@link UserController} with the given {@link UserManagement}
	 * @param userManagement
	 */
	UserController(UserManagement userManagement) {
		Assert.notNull(userManagement, "UserManagement must not be null!");
		this.userManagement = userManagement;
	}
}