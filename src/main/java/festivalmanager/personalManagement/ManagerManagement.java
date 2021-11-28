package festivalmanager.personalManagement;

import festivalmanager.authentication.UserManagement;
import festivalmanager.authentication.UserRepository;
import org.salespointframework.useraccount.UserAccountManagement;
import org.springframework.stereotype.Service;

@Service
public class ManagerManagement extends UserManagement {
	public ManagerManagement(UserRepository users, UserAccountManagement userAccounts) {
		super(users, userAccounts);
	}



	public void createStaffAccount(){}

	public boolean deleteStaffAccount(){
		return true;
	}

	public void checkFinances(){

	}
	public void vizualizeFinances(){

	}


}
