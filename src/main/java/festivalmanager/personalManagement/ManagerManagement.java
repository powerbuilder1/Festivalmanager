package festivalmanager.personalManagement;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserForm;
import festivalmanager.authentication.UserManagement;
import festivalmanager.authentication.UserRepository;
import festivalmanager.catering.CateringManagement;
import festivalmanager.catering.Food;
import festivalmanager.catering.FoodCatalog;
import festivalmanager.catering.NewFoodItemForm;
import festivalmanager.stock.StockManagment;
import org.hibernate.usertype.UserType;
import org.javamoney.moneta.Money;
import org.salespointframework.core.Currencies;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.useraccount.UserAccountManagement;
import org.springframework.stereotype.Service;

@Service
public class ManagerManagement extends UserManagement {




	public ManagerManagement(UserRepository users, UserAccountManagement userAccounts) {
		super(users, userAccounts);
	}

	public void deleteUser(User user) {
			users.deleteById(user.getId());
	}

	public void editUser(User user, UserForm userForm) {
			user.setAddress(userForm.getAddress());
			user.setPosition(userForm.getPosition());
			users.save(user);
	}


	public void checkFinances(){
	//Finances not implemented yet
	}
	public void vizualizeFinances(){
	//Finances not implemented yet
	}


}
