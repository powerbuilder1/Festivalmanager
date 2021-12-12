package festivalmanager.authentication;

import javax.validation.constraints.NotEmpty;

public class UserForm {
	@NotEmpty(message = "{RegistrationForm.name.NotEmpty}") //
	private  String name;

	@NotEmpty(message = "{RegistrationForm.password.NotEmpty}") //
	private  String password;

	@NotEmpty(message = "{RegistrationForm.address.NotEmpty}") // s
	private  String address;

	@NotEmpty(message = "{RegistrationForm.position.NotEmpty}") // s
	private  String position;

	@NotEmpty(message = "{RegistrationForm.workplace.NotEmpty}")
	private String workPlace;

	private Long festivalId;

	public UserForm(String name, String password, String address, String position, String workPlace,  Long festivalId) {

		this.name = name;
		this.password = password;
		this.address = address;
		this.position = position;
		this.workPlace = workPlace;
		this.festivalId = festivalId;
	}


	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}

	public String getPosition() {
		return position;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public Long getFestivalId() { return festivalId; }

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public void setFestivalId(Long festivalId) {
		this.festivalId = festivalId;
	}
}
