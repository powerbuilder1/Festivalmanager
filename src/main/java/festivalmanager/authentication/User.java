package festivalmanager.authentication;

import org.salespointframework.useraccount.UserAccount;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User{

	private @Id @GeneratedValue long id;
	private String address;
	private String position;

	@OneToOne //
	private UserAccount userAccount;

	@SuppressWarnings("unused")
	private User() {

	}



	public User(String position, UserAccount userAccount) {

		this.position = position;
		this.userAccount = userAccount;
	}


//	public User(String address, UserAccount userAccount) {
//		this.address = address;
//		this.userAccount = userAccount;
//	}



	public long getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}