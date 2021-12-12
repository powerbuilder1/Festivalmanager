package festivalmanager.personalManagement;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserForm;
import festivalmanager.authentication.UserManagement;
import festivalmanager.authentication.UserRepository;
import festivalmanager.festival.Festival;
import festivalmanager.location.Location;
import festivalmanager.location.LocationManagement;
import festivalmanager.location.LocationRepository;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.useraccount.UserAccountManagement;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;


@Service
public class ManagerManagement extends UserManagement {


	LocationManagement locationManagement;

	public ManagerManagement(UserRepository users, UserAccountManagement userAccounts) {
		super(users, userAccounts);
	}



	public void deleteUser(User user) {
				users.deleteById(user.getId());
	}

	public void editUser(User user, UserForm userForm) {
		user.setAddress(userForm.getAddress());

		switch (userForm.getPosition()){
				case "Planning":
				case "Catering":
				case "Festivalleiter":
					user.setPosition(userForm.getPosition());
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + userForm.getPosition());
			}

				user.setWorkPlace(userForm.getWorkPlace());
			users.save(user);
	}


	public void checkFinances(){
	//Finances not implemented yet
	}
	public void vizualizeFinances(){
	//Finances not implemented yet
	}


}
