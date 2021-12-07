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


		List.of(new UserForm("Planning", "123456", "Zuhause","Planning", "Gisela")).forEach(userManagement::createPlanningStaff);
		List.of(new UserForm("Catering", "123456", "Zuhause","Catering","Arteum")).forEach(userManagement::createCateringStaff);
		userAccountManagement.create("Hans", Password.UnencryptedPassword.of("123456"), Role.of("CUSTOMER"));
		userAccountManagement.create("Boss", Password.UnencryptedPassword.of("123456"), Role.of("BOSS"));





//		userAccountManagement.create("Catering", Password.UnencryptedPassword.of("123456"), Role.of("CATERING"));
//		userAccountManagement.create("Planning", Password.UnencryptedPassword.of("123456"), Role.of("PLANNING"));

	}
}
