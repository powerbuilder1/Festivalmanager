package festivalmanager.authentication;

import festivalmanager.festival.Festival;
import festivalmanager.location.Location;
import org.salespointframework.useraccount.UserAccount;

import festivalmanager.communication.Participants;

import java.util.Set;

import javax.persistence.*;
/**
 * @author Conrad, Philipp, Franz, Aleksey
 * A class to represent a festival
 */
@Entity
public class User {
	/**
	 * User identifier (primary key) (auto-generated) {@link Long}
	 */
	private @Id @GeneratedValue long id;

	/**
	 * User address {@link String}
	 */
	private String address;

	/**
	 * The position of a user {@link String}
	 */
	private String position;

	/**
	 * The name of a user {@link String}
	 */
	private String name;

	/**
	 * The password of a user {@link String}
	 */
	private String password;

	/**
	 * The work place of a user {@link String}
	 */
	private String workPlace;



	/**
	 * The account of a user {@link UserAccount}
	 */
	@OneToOne //
	private UserAccount userAccount;

	/**
	 * The festival {@link Festival}
	 */
	@ManyToOne
	@JoinColumn(name = "festival_id", nullable = false)
	private Festival festival;

	// used for chat
	@OneToMany(mappedBy = "user")
	Set<Participants> participants;

	/**
	 * default constructor
	 */
	@SuppressWarnings("unused")
	private User() {

	}

	/**
	 * User constructor
	 *
	 * @param position the name of the festival {@link String}
	 * @param userAccount the location of the festival {@link Location}
	 */

	public User(String position, UserAccount userAccount) {
		this.position = position;
		this.userAccount = userAccount;
	}

	/**
	 * getter
	 *
	 * @return the id {@link Long}
	 */
	public long getId() {
		return id;
	}

	/**
	 * getter
	 *
	 * @return the name {@link String}
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter
	 *
	 * @return the password {@link String}
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * getter
	 *
	 * @return the workplace {@link String}
	 */
	public String getWorkPlace() {
		return workPlace;
	}


	/**
	 * setter
	 *
	 * @param workPlace the work place to set {@link String}
	 */
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	/**
	 * setter
	 *
	 * @param name the name to set {@link String}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * setter
	 *
	 * @param password the password  to set {@link String}
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * getter
	 *
	 * @return the address {@link String}
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * setter
	 *
	 * @param address the address  to set {@link String}
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * getter
	 *
	 * @return the user account {@link String}
	 */
	public UserAccount getUserAccount() {
		return userAccount;
	}

	/**
	 * getter
	 *
	 * @return the position  {@link String}
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * setter
	 *
	 * @param position the position  to set {@link String}
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * getter
	 *
	 * @return the festival  {@link String}
	 */
	public Festival getFestival() {
		return festival;
	}

	/**
	 * setter
	 *
	 * @param festival the festival  to set {@link String}
	 */
	public void setFestival(Festival festival) {
		this.festival = festival;
	}



}