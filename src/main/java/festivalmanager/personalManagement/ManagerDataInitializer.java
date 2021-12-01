package festivalmanager.personalManagement;

import org.salespointframework.core.DataInitializer;

import org.salespointframework.useraccount.Password;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccountManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ManagerDataInitializer implements DataInitializer {

	private static final Logger LOG = LoggerFactory.getLogger(ManagerDataInitializer.class);
	private final ManagerManagement managerManagement;
	private final UserAccountManagement userAccountManagement;


	public ManagerDataInitializer(UserAccountManagement userAccountManagement, ManagerManagement managerManagement) {
		Assert.notNull(userAccountManagement, "UserAccountManagement must not be null!");
		Assert.notNull(managerManagement, "ManagerRepository must not be null!");

		this.userAccountManagement = userAccountManagement;
		this.managerManagement = managerManagement;
	}


	@Override
	public void initialize() {
		if (userAccountManagement.findByUsername("boss").isPresent()) {
			return;
		}

		userAccountManagement.create("manager", Password.UnencryptedPassword.of("manager"), Role.of("BOSS"));
		userAccountManagement.create("catering", Password.UnencryptedPassword.of("catering"), Role.of("CATERING"));
		var password = "manager";


	}
}