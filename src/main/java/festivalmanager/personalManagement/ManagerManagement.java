package festivalmanager.personalManagement;

import org.springframework.stereotype.Service;

@Service
public class ManagerManagement {
	public ManagerRepository managerRepository;

	public ManagerManagement(ManagerRepository managerRepository) {
		this.managerRepository = managerRepository;
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
