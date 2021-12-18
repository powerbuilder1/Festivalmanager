package festivalmanager.catering;

import javax.validation.constraints.*;

public class NewFoodItemForm {

	@NotEmpty
	private  String name;
	@NotNull
	@Positive
	private Double price;

	public NewFoodItemForm(String name, Double price) {
		this.name = name;
		this.price = price;
	}

	public NewFoodItemForm() {
	}
	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
