package festivalmanager.stock;

import org.salespointframework.catalog.ProductIdentifier;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ReorderForm implements Serializable {
	private @NotEmpty ProductIdentifier foodItemId;
	private @NotEmpty int amount;

	public ReorderForm(ProductIdentifier foodItemId, int amount) {
		this.foodItemId = foodItemId;
		this.amount = amount;
	}

	public ReorderForm() {
	}

	public ProductIdentifier getFoodItemId() {
		return foodItemId;
	}

	public void setFoodItemId(ProductIdentifier foodItemId) {
		this.foodItemId = foodItemId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void validate(Errors errors) {
		// Complex validation goes here
	}
}
