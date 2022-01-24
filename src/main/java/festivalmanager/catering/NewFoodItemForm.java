package festivalmanager.catering;

import javax.validation.constraints.*;

public class NewFoodItemForm {

	@NotEmpty
	private  String name;
	@NotNull
	@Positive
	private Double price;

	/**
	 *
	 * @param name
	 * @param price
	 */
	public NewFoodItemForm(String name, Double price) {
		this.name = name;
		this.price = price;
	}

	public NewFoodItemForm() {
	}

	/**
	 * getter for name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter for price
	 * @return
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * setter for name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * setter for price
	 * @param price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

}
