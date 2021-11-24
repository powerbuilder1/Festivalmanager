package festivalmanager.catering;

import javax.validation.constraints.NotEmpty;

public class NewFoodItemForm {
	private final @NotEmpty String name;
	private final @NotEmpty double price;

	public NewFoodItemForm(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
}
