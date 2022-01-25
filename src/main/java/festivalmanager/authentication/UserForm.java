package festivalmanager.authentication;


import javax.validation.constraints.NotEmpty;

public class UserForm {
	/**
	 * the name entered in the {@link UserForm} {@link String}
	 */
	@NotEmpty(message = "{RegistrationForm.name.NotEmpty}") //
	private  String name;
	/**
	 * the  password in the {@link UserForm} {@link String}
	 */
	@NotEmpty(message = "{RegistrationForm.password.NotEmpty}") //
	private  String password;

	/**
	 * the address entered in the {@link UserForm} {@link String}
	 */
	@NotEmpty(message = "{RegistrationForm.address.NotEmpty}") // s
	private  String address;

	/**
	 * the  position entered in the {@link UserForm} {@link String}
	 */
	@NotEmpty(message = "{RegistrationForm.position.NotEmpty}") // s
	private  String position;

	/**
	 * the workplace entered in the {@link UserForm} {@link String}
	 */
	@NotEmpty(message = "{RegistrationForm.workplace.NotEmpty}")
	private String workPlace;

	/**
	 * the festival id entered in the {@link UserForm} {@link Long}
	 */
	private Long festivalId;

	/**
	 * UserForm constructor
	 *
	 * @param name  {@link String}
	 * @param password  {@link String}
	 * @param address  {@link String}
	 * @param position {@link String}
	 * @param workPlace {@link String}
	 * @param festivalId {@link Long}
	 */

	public UserForm(String name, String password, String address, String position, String workPlace,  Long festivalId) {

		this.name = name;
		this.password = password;
		this.address = address;
		this.position = position;
		this.workPlace = workPlace;
		this.festivalId = festivalId;
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
	 * @return the address {@link String}
	 */

	public String getAddress() {
		return address;
	}

	/**
	 * getter
	 *
	 * @return the position {@link String}
	 */

	public String getPosition() {
		return position;
	}

	/**
	 * getter
	 *
	 * @return the work place {@link String}
	 */

	public String getWorkPlace() {
		return workPlace;
	}

	/**
	 * getter
	 *
	 * @return the festival id {@link Long}
	 */

	public Long getFestivalId() { return festivalId; }

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
	 * @param password the password to set {@link String}
	 */

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * setter
	 *
	 * @param address the address to set {@link String}
	 */

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * setter
	 *
	 * @param position the position to set {@link String}
	 */

	public void setPosition(String position) {
		this.position = position;
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
	 * @param festivalId the festival id to set {@link String}
	 */

	public void setFestivalId(Long festivalId) {
		this.festivalId = festivalId;
	}
}
