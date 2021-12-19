package festivalmanager.stock;

import org.salespointframework.catalog.ProductIdentifier;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ReorderForm {
	private @NotNull ProductIdentifier foodItemId;
	private @NotNull @Positive int amount;

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

}
