package festivalmanager.authentication;

import festivalmanager.location.Location;
import org.salespointframework.useraccount.UserAccount;

import festivalmanager.communication.Participants;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {

	private @Id @GeneratedValue long id;
	private String address;
	private String position;
	private String name;
	private String password;
	private String workPlace;



	@OneToOne //
	private UserAccount userAccount;

	// used for chat
	@OneToMany(mappedBy = "user")
	Set<Participants> participants;

	@SuppressWarnings("unused")
	private User() {

	}

	public User(String position, UserAccount userAccount) {
		this.position = position;
		this.userAccount = userAccount;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
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