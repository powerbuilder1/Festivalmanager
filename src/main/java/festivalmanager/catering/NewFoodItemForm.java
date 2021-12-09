package festivalmanager.catering;

import festivalmanager.festival.Festival;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class NewFoodItemForm {

	@NotEmpty
	@Size(min = 3)
	private  String name;
	@NotNull
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

	public void validate(Errors errors) {
		// Complex validation goes here
	}
}
