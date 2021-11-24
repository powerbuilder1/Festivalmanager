package festivalmanager.authentication;

import org.salespointframework.core.DataInitializer;
import org.salespointframework.useraccount.Password;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccountManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component
public class UserDataInitializer implements DataInitializer {

	private static final Logger LOG = LoggerFactory.getLogger(UserDataInitializer.class);

	private final UserAccountManagement userAccountManagement;
	private final UserManagement userManagement;

	UserDataInitializer(UserAccountManagement userAccountManagement, UserManagement userManagement) {

		Assert.notNull(userAccountManagement, "UserAccountManagement must not be null!");
		Assert.notNull(userManagement, "UserRepository must not be null!");

		this.userAccountManagement = userAccountManagement;
		this.userManagement = userManagement;
	}

	@Override
	public void initialize() {

		List.of(new UserForm("Hans", "123456", "Zuhause")).forEach(userManagement::createUser);
		List.of(new UserForm("BOSS", "123", "Zuhause")).forEach(userManagement::createBoss);
		List.of(new UserForm("Plannuing", "123456", "Zuhause")).forEach(userManagement::createPlanningStaff);
		List.of(new UserForm("Catering", "123456", "Zuhause")).forEach(userManagement::createCateringStaff);
	}
}
