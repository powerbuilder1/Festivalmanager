package festivalmanager.authentication;

import org.hibernate.usertype.UserType;
import org.salespointframework.useraccount.Password;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccountManagement;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import festivalmanager.catering.CateringController;

@Service
@Transactional
public class UserManagement {
	public static final Role CUSTOMER_ROLE = Role.of("CUSTOMER");
	public static final Role BOSS_ROLE = Role.of("BOSS");
	public static final Role PLANNING_ROLE = Role.of("PLANNING");
	public static final Role CATERING_ROLE = Role.of("CATERING");

	protected final UserRepository users;
	protected final UserAccountManagement userAccounts;


	protected UserManagement(UserRepository users, UserAccountManagement userAccounts) {

		Assert.notNull(users, "UserRepository must not be null!");
		Assert.notNull(userAccounts, "UserAccountManagement must not be null!");

		this.users = users;
		this.userAccounts = userAccounts;
	}


	public User createUser(UserForm form) {

		Assert.notNull(form, "Registration form must not be null!");

		var password = Password.UnencryptedPassword.of(form.getPassword());
		var userAccount = userAccounts.create(form.getName(), password, CUSTOMER_ROLE);

		return users.save(new User(form.getPosition(), userAccount));
	}

	public User createBoss(UserForm form) {

		Assert.notNull(form, "Registration form must not be null!");

		var password = Password.UnencryptedPassword.of(form.getPassword());
		var userAccount = userAccounts.create(form.getName(), password, BOSS_ROLE);

		return users.save(new User(form.getPosition(), userAccount));
	}

	public User createPlanningStaff(UserForm form) {

		Assert.notNull(form, "Registration form must not be null!");

		var password = Password.UnencryptedPassword.of(form.getPassword());
		var userAccount = userAccounts.create(form.getName(), password, PLANNING_ROLE);

		return users.save(new User(form.getPosition(), userAccount));
	}

	public User createCateringStaff(UserForm form) {

		Assert.notNull(form, "Registration form must not be null!");

		var password = Password.UnencryptedPassword.of(form.getPassword());
		var userAccount = userAccounts.create(form.getName(), password, CATERING_ROLE);

		return users.save(new User(form.getPosition(), userAccount));
	}

	public Streamable<User> findAll() {
		return users.findAll();
	}


}
