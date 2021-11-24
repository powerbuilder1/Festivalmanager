package festivalmanager.catering;

import org.springframework.validation.Errors;

import javax.validation.constraints.NotEmpty;

public class NewFoodItemForm {

	private final @NotEmpty String name;
	private final @NotEmpty Double price;

	public NewFoodItemForm(String name, Double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public void validate(Errors errors) {
		// Complex validation goes here
	}
}
