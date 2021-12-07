package festivalmanager.authentication;

import org.salespointframework.core.DataInitializer;
import org.salespointframework.useraccount.Password;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccountManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Order(30)
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

<<<<<<< HEAD

		List.of(new UserForm("Planning", "123456", "Zuhause","Planning", "Gisela")).forEach(userManagement::createPlanningStaff);
		List.of(new UserForm("Catering", "123456", "Zuhause","Catering","Arteum")).forEach(userManagement::createCateringStaff);
		userAccountManagement.create("Hans", Password.UnencryptedPassword.of("123456"), Role.of("CUSTOMER"));
		userAccountManagement.create("Boss", Password.UnencryptedPassword.of("123456"), Role.of("BOSS"));





//		userAccountManagement.create("Catering", Password.UnencryptedPassword.of("123456"), Role.of("CATERING"));
//		userAccountManagement.create("Planning", Password.UnencryptedPassword.of("123456"), Role.of("PLANNING"));
=======
		List.of(new UserForm("Hans", "123456", "Zuhause", "Customer")).forEach(userManagement::createUser);
		List.of(new UserForm("Planning", "123456", "Zuhause", "Planning")).forEach(userManagement::createPlanningStaff);
		List.of(new UserForm("Catering", "123456", "Zuhause", "Catering")).forEach(userManagement::createCateringStaff);
		List.of(new UserForm("Boss", "123456", "Zuhause", "BOSS"),
				new UserForm("manager", "manager", "Zuhause", "BOSS")).forEach(userManagement::createBoss);
		List.of(new UserForm("SYSTEM", "abcdefg", "", "SYSTEM")).forEach(userManagement::createSystem);

		// userAccountManagement.create("Catering",
		// Password.UnencryptedPassword.of("123456"), Role.of("CATERING"));
		// userAccountManagement.create("Hans",
		// Password.UnencryptedPassword.of("123456"), Role.of("CUSTOMER"));
		// userAccountManagement.create("Planning",
		// Password.UnencryptedPassword.of("123456"), Role.of("PLANNING"));
>>>>>>> a66caba43db1d322b246ff29d2bbc388e43043e3

	}
}
