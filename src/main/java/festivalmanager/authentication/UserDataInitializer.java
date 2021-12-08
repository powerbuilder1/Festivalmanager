package festivalmanager.authentication;

import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import org.salespointframework.core.DataInitializer;
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
	private final FestivalManagement festivalManagement;

	UserDataInitializer(UserAccountManagement userAccountManagement, UserManagement userManagement, FestivalManagement festivalManagement) {
		this.festivalManagement = festivalManagement;

		Assert.notNull(userAccountManagement, "UserAccountManagement must not be null!");
		Assert.notNull(userManagement, "UserRepository must not be null!");

		this.userAccountManagement = userAccountManagement;
		this.userManagement = userManagement;
	}

	@Override
	public void initialize() {


//		userAccountManagement.create("Catering", Password.UnencryptedPassword.of("123456"), Role.of("CATERING"));
//		userAccountManagement.create("Planning", Password.UnencryptedPassword.of("123456"), Role.of("PLANNING"));

		Long festivalId = festivalManagement.findAllFestivals().toList().get(0).getId();
		Long festivalId1 = festivalManagement.findAllFestivals().toList().get(1).getId();

		List.of(new UserForm("Hans", "123456", "Zuhause", "Customer",null, festivalId)).forEach(userManagement::createUser);
		List.of(new UserForm("Planning", "123456", "Zuhause", "Planning",null, festivalId)).forEach(userManagement::createPlanningStaff);
		List.of(
				new UserForm("Catering", "123456", "Zuhause", "Catering",null, festivalId),
				new UserForm("Catering1", "123456", "Zuhause", "Catering",null, festivalId1)
		).forEach(userManagement::createCateringStaff);
		List.of(new UserForm("Boss", "123456", "Zuhause", "BOSS",null, festivalId),
				new UserForm("manager", "manager", "Zuhause", "BOSS",null, festivalId)).forEach(userManagement::createBoss);
		List.of(new UserForm("SYSTEM", "abcdefg", "", "SYSTEM",null, festivalId)).forEach(userManagement::createSystem);
		List.of(
				new UserForm("FestivalDirector", "123", "Zuhause", "FESTIVALDIRECTOR",null, festivalId),
				new UserForm("FestivalDirector1", "123", "Zuhause", "FESTIVALDIRECTOR",null, festivalId1)
		).forEach(userManagement::createFestivalDirector);

		// userAccountManagement.create("Catering",
		// Password.UnencryptedPassword.of("123456"), Role.of("CATERING"));
		// userAccountManagement.create("Hans",
		// Password.UnencryptedPassword.of("123456"), Role.of("CUSTOMER"));
		// userAccountManagement.create("Planning",
		// Password.UnencryptedPassword.of("123456"), Role.of("PLANNING"));

	}
}
