package festivalmanager.authentication;

import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import org.salespointframework.useraccount.Password;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManagement;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;



@Service
@Transactional
public class UserManagement {
	public static final Role CUSTOMER_ROLE = Role.of("CUSTOMER");
	public static final Role BOSS_ROLE = Role.of("BOSS");
	public static final Role PLANNING_ROLE = Role.of("PLANNING");
	public static final Role CATERING_ROLE = Role.of("CATERING");
	public static final Role SYSTEM_ROLE = Role.of("SYSTEM");
	public static final Role FESTIVALDIRECTOR_ROLE = Role.of("FESTIVALDIRECTOR");
	public static final Role SECURITY_ROLE = Role.of("SECURITY");

	protected final UserRepository users;
	protected final UserAccountManagement userAccounts;
	protected static FestivalManagement festivalManagement;

	/**
	 * Creates a new {@link UserManagement} with the given {@link UserRepository} and
	 * {@link UserAccountManagement}.
	 *
	 * @param users must not be {@literal null}.
	 * @param userAccounts must not be {@literal null}.
	 */

	protected UserManagement(UserRepository users, UserAccountManagement userAccounts) {

		Assert.notNull(users, "UserRepository must not be null!");
		Assert.notNull(userAccounts, "UserAccountManagement must not be null!");

		this.users = users;
		this.userAccounts = userAccounts;

	}

	public void setFestivalManagement(FestivalManagement festivalManagement) {
		this.festivalManagement = festivalManagement;
	}

	/**
	 * Creates a new {@link User} using the information given in the {@link UserForm}.
	 *
	 * @param form must not be {@literal null}.
	 * @return the new {@link User} instance.
	 */

	public User createUser(UserForm form) {

		Assert.notNull(form, "Registration form must not be null!");
		var password = Password.UnencryptedPassword.of(form.getPassword());
		var userAccount = userAccounts.create(form.getName(), password, CUSTOMER_ROLE);
		User user = new User(form.getPosition(), userAccount);
		user.setAddress(form.getAddress());
		user.setName(form.getName());
		user.setPosition(form.getPosition());
		user.setFestival(festivalManagement.findById(form.getFestivalId()));
		return users.save(user);
	}
	/**
	 * Creates a new {@link User} using the information given in the {@link UserForm}.
	 *
	 * @param form must not be {@literal null}.
	 * @return the new {@link User} instance.
	 */
	public User createBoss(UserForm form) {

		Assert.notNull(form, "Registration form must not be null!");

		var password = Password.UnencryptedPassword.of(form.getPassword());
		var userAccount = userAccounts.create(form.getName(), password, BOSS_ROLE);
		User user = new User(form.getPosition(), userAccount);
		user.setAddress(form.getAddress());
		user.setName(form.getName());
		user.setPosition(form.getPosition());
		user.setFestival(festivalManagement.findById(form.getFestivalId()));
		return users.save(user);
	}
	/**
	 * Creates a new {@link User} using the information given in the {@link UserForm}.
	 *
	 * @param form must not be {@literal null}.
	 * @return the new {@link User} instance.
	 */
	public User createPlanningStaff(UserForm form) {

		Assert.notNull(form, "Registration form must not be null!");

		var password = Password.UnencryptedPassword.of(form.getPassword());
		var userAccount = userAccounts.create(form.getName(), password, PLANNING_ROLE);
		User user = new User(form.getPosition(), userAccount);
		user.setAddress(form.getAddress());
		user.setWorkPlace(form.getWorkPlace());
		user.setName(form.getName());
		user.setPosition(form.getPosition());
		user.setFestival(festivalManagement.findById(form.getFestivalId()));
		return users.save(user);
	}
	/**
	 * Creates a new {@link User} using the information given in the {@link UserForm}.
	 *
	 * @param form must not be {@literal null}.
	 * @return the new {@link User} instance.
	 */
	public User createCateringStaff(UserForm form) {

		Assert.notNull(form, "Registration form must not be null!");

		var password = Password.UnencryptedPassword.of(form.getPassword());
		var userAccount = userAccounts.create(form.getName(), password, CATERING_ROLE);
		User user = new User(form.getPosition(), userAccount);
		user.setAddress(form.getAddress());
		user.setWorkPlace(form.getWorkPlace());
		user.setName(form.getName());
		user.setPosition(form.getPosition());
		user.setFestival(festivalManagement.findById(form.getFestivalId()));
		return users.save(user);
	}
	/**
	 * Creates a new {@link User} using the information given in the {@link UserForm}.
	 *
	 * @param form must not be {@literal null}.
	 * @return the new {@link User} instance.
	 */
	public User createSystem(UserForm form) {

		Assert.notNull(form, "Registration form must not be null!");

		var password = Password.UnencryptedPassword.of(form.getPassword());
		var userAccount = userAccounts.create(form.getName(), password, SYSTEM_ROLE);
		User user = new User(form.getPosition(), userAccount);
		user.setAddress(form.getAddress());
		user.setName(form.getName());
		user.setPosition(form.getPosition());
		user.setFestival(festivalManagement.findById(form.getFestivalId()));
		return users.save(user);
	}
	/**
	 * Creates a new {@link User} using the information given in the {@link UserForm}.
	 *
	 * @param form must not be {@literal null}.
	 * @return the new {@link User} instance.
	 */
	public User createFestivalDirector(UserForm form) {

		Assert.notNull(form, "Registration form must not be null!");

		var password = Password.UnencryptedPassword.of(form.getPassword());
		var userAccount = userAccounts.create(form.getName(), password, FESTIVALDIRECTOR_ROLE);
		User user = new User(form.getPosition(), userAccount);
		user.setAddress(form.getAddress());
		user.setName(form.getName());
		user.setPosition(form.getPosition());
		user.setFestival(festivalManagement.findById(form.getFestivalId()));
		return users.save(user);
	}
	/**
	 * Creates a new {@link User} using the information given in the {@link UserForm}.
	 *
	 * @param form must not be {@literal null}.
	 * @return the new {@link User} instance.
	 */
	public User createSecurityStaff (UserForm form) {

		Assert.notNull(form, "Registration form must not be null!");

		var password = Password.UnencryptedPassword.of(form.getPassword());
		var userAccount = userAccounts.create(form.getName(), password, PLANNING_ROLE);
		User user = new User(form.getPosition(), userAccount);
		user.setAddress(form.getAddress());
		user.setWorkPlace(form.getWorkPlace());
		user.setName(form.getName());
		user.setPosition(form.getPosition());
		user.setFestival(festivalManagement.findById(form.getFestivalId()));
		return users.save(user);
	}

	/**
	 * Returns all {@link User}s currently available in the system.
	 *
	 * @return all {@link User} entities.
	 */
	public Streamable<User> findAll() {
		return users.findAll();
	}

	/**
	 * Returns all {@link User}s  with matching {@param id} currently available in the system.
	 * @param id must not be {@literal null}.
	 * @return all {@link User} entities.
	 */

	public User findById(long id) {
		return users.findById(id).orElse(null);
	}

	/**
	 * Returns all users with the given name
	 *
	 * @param name
	 * @return
	 */

	public Streamable<User> findAllByName(String name) {
		return users.findAll().filter(user -> user.getName().equals(name));
	}

	/**
	 * Returns the user with the given name
	 *
	 * @param name
	 * @return
	 */
	public User findByName(String name) {
		Streamable<User> userList = users.findAll().filter(user -> user.getName().equals(name));
		if (!userList.isEmpty()) {
			return userList.toList().get(0);
		}
		return null;
	}

	/**
	 * Returns the user with the user account
	 *
	 * @param useraccount
	 * @return
	 */
	public User findUserByUserAccount(UserAccount useraccount) {
		return users.findUserByUserAccount(useraccount);
	}

	public void deleteById(long id) {
		users.deleteById(id);
	}

}
