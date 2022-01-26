package festivalmanager.lineup;
import org.javamoney.moneta.Money;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class BandForm {

	private  long id;
	private @NotEmpty String name;
	private @NotEmpty double price;
	private @NotEmpty String stage;
	private @NotEmpty String  performanceHour;
	/**
	 * BandForm constructor
	 *
	 * @param name the name of the band form {@link String}
	 * @param price the price of the band form {@link Money}
	 * @param stage the stage of the band form {@link String}
	 * @param performanceHour the performance hour of the band form {@link String}
	 * @param id the id of the band form {@link Long}

	 */

	public BandForm(String name, double price, String stage, String performanceHour, Long id) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.performanceHour = performanceHour;
		this.stage = stage;
	}
	/**
	 * default constructor
	 */
	public BandForm() {
	}

	/**
	 * getter for name
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * getter for stage
	 * @return
	 */
	public String getStage() {
		return this.stage;
	}
	/**
	 * getter for performance Hour
	 * @return
	 */
	public String getPerformanceHour() {
		return this.performanceHour;
	}
	/**
	 * getter for price
	 * @return
	 */
	public double getPrice() {
		return this.price;
	}
	/**
	 * getter for id
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "BandForm{" +
				"id=" + getId() +
				", name='" + getName() + "'" +
				", price=" + getPrice() +"'" +
				", stage='" + getStage() + "'" +
				", performanceHour='" + getPerformanceHour()+ "'" +
				'}';
	}
	/**
	 * setter
	 *
	 * @param name to set the name {@link String}
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * setter
	 *
	 * @param price  to set the price {@link Double}
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * setter
	 *
	 * @param stage  to set the stage {@link String}
	 */
	public void setStage(String stage) {
		this.stage = stage;
	}
	/**
	 * setter
	 *
	 * @param performanceHour  to set the performance hour {@link String}
	 */
	public void setPerformanceHour(String performanceHour) {
		this.performanceHour = performanceHour;
	}
}



