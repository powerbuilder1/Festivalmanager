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

	public UserForm(String name, String password, String address, String position) {

		this.name = name;
		this.password = password;
		this.address = address;
		this.position = position;
	}

	public UserForm() {
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
}
