package festivalmanager.personalManagement;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserForm;
import festivalmanager.authentication.UserManagement;
import festivalmanager.authentication.UserRepository;
import festivalmanager.location.LocationManagement;
import org.salespointframework.useraccount.UserAccountManagement;
import org.springframework.stereotype.Service;


@Service
public class ManagerManagement extends UserManagement {


	LocationManagement locationManagement;

	/**
	 * Creates a new {@link ManagerManagement} with the given {@link UserRepository} and
	 * {@link UserAccountManagement}
	 *
	 * @param users must not be {@literal null}.
	 * @param userAccounts must not be {@literal null}.
	 */
	public ManagerManagement(UserRepository users, UserAccountManagement userAccounts) {
		super(users, userAccounts);
	}


	/**
	 * delete {@param user} from the {@link UserRepository}
	 * @param user
	 */
	public void deleteUser(User user) {
				users.deleteById(user.getId());
	}

	/**
	 * change user details from the {@link UserRepository}
	 * @param user
	 * @param userForm
	 */
	public void editUser(User user, UserForm userForm) {
		user.setAddress(userForm.getAddress());
		switch (userForm.getPosition()){
				case "Planning":
				case "Catering":
				case "Festivalleiter":
				case "Security":
					user.setPosition(userForm.getPosition());
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + userForm.getPosition());
			}
			user.setWorkPlace(userForm.getWorkPlace());
			user.setFestival(festivalManagement.findById(userForm.getFestivalId()));
			users.save(user);
	}

}
