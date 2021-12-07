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
	public static final Role SYSTEM_ROLE = Role.of("SYSTEM");

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
		User user = new User(form.getPosition(), userAccount);
		user.setAddress(form.getAddress());
		user.setName(form.getName());
		user.setPosition(form.getPosition());
		return users.save(user);
	}

	public User createBoss(UserForm form) {

		Assert.notNull(form, "Registration form must not be null!");

		var password = Password.UnencryptedPassword.of(form.getPassword());
		var userAccount = userAccounts.create(form.getName(), password, BOSS_ROLE);
		User user = new User(form.getPosition(), userAccount);
		user.setAddress(form.getAddress());
		user.setName(form.getName());
		user.setPosition(form.getPosition());
		return users.save(user);
	}

	public User createPlanningStaff(UserForm form) {

		Assert.notNull(form, "Registration form must not be null!");

		var password = Password.UnencryptedPassword.of(form.getPassword());
		var userAccount = userAccounts.create(form.getName(), password, PLANNING_ROLE);
		User user = new User(form.getPosition(), userAccount);
		user.setAddress(form.getAddress());
		user.setWorkPlace(form.getWorkPlace());
		user.setName(form.getName());
		user.setPosition(form.getPosition());
		return users.save(user);
	}

	public User createCateringStaff(UserForm form) {

		Assert.notNull(form, "Registration form must not be null!");

		var password = Password.UnencryptedPassword.of(form.getPassword());
		var userAccount = userAccounts.create(form.getName(), password, CATERING_ROLE);
		User user = new User(form.getPosition(), userAccount);
		user.setAddress(form.getAddress());
		user.setWorkPlace(form.getWorkPlace());
		user.setName(form.getName());
		user.setPosition(form.getPosition());
		return users.save(user);
	}

	public User createSystem(UserForm form) {

		Assert.notNull(form, "Registration form must not be null!");

		var password = Password.UnencryptedPassword.of(form.getPassword());
		var userAccount = userAccounts.create(form.getName(), password, SYSTEM_ROLE);
		User user = new User(form.getPosition(), userAccount);
		user.setAddress(form.getAddress());
		user.setName(form.getName());
		user.setPosition(form.getPosition());
		return users.save(user);
	}

	public Streamable<User> findAll() {
		return users.findAll();
	}

	public User findById(long id) {
		return users.findById(id).orElse(null);
	}

	public User findByName(String name) {
		Streamable<User> userList = users.findAll().filter(user -> user.getName().equals(name));
		if (!userList.isEmpty()) {
			return userList.toList().get(0);
		}
		return null;
	}

}
