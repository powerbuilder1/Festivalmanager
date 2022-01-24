package festivalmanager.stock;

import org.salespointframework.catalog.ProductIdentifier;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ReorderForm {
	private @NotNull ProductIdentifier foodItemId;
	private @NotNull @Positive int amount;

	/**
	 *
	 * @param foodItemId
	 * @param amount
	 */
	public ReorderForm(ProductIdentifier foodItemId, int amount) {
		this.foodItemId = foodItemId;
		this.amount = amount;
	}

	public ReorderForm() {
	}

	/**
	 * getter for foodItem ProductIdentifier
	 * @return
	 */
	public ProductIdentifier getFoodItemId() {
		return foodItemId;
	}


	/**
	 * setter for foodItem ProductIdentifier
	 * @param foodItemId
	 */
	public void setFoodItemId(ProductIdentifier foodItemId) {
		this.foodItemId = foodItemId;
	}

	/**
	 * getter for amount
	 * @return
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * setter for amount
	 * @param amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
