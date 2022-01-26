package festivalmanager.lineup;

import festivalmanager.location.Location;
import org.javamoney.moneta.Money;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * @author Conrad, Philipp, Franz, Aleksey
 * A class to represent a festival
 */
@Entity
public class Band {
	private @Id
	@GeneratedValue
	long id;
	public String name1;
	public Money price;
	public String stage;
	public String performanceHour;

	/**
	 * default constructor
	 */
	public Band() {
	}

	/**
	 * Band constructor
	 *
	 * @param name1 the name of the band {@link String}
	 * @param price the price of the band {@link Money}
	 * @param stage the stage in which the band is going to perform {@link String}
	 * @param performanceHour the hour in which the band is going to play in the festival {@link String}
	 */
	public Band(String name1, Money price, String stage, String performanceHour) {
		this.setPrice(price);
		this.setName1(name1);
		this.setStage(stage);
		this.setPerformanceHour(performanceHour);
	}

	/**
	 * getter
	 *
	 * @return the name {@link String}
	 */
	public String getName1() {
		return name1;
	}
	/**
	 * getter
	 *
	 * @return the stage {@link String}
	 */
	public String getStage() {
		return stage;
	}
	/**
	 * getter
	 *
	 * @return the performancehour {@link String}
	 */
	public String getPerformanceHour() {
		return performanceHour;
	}
	/**
	 * getter
	 *
	 * @return the price {@link Money}
	 */
	public Money getPrice() {
		return price;
	}
	/**
	 * getter
	 *
	 * @return the id  {@link Long}
	 */
	public Long getId() {
		return id;
	}
	/**
	 * setter
	 *
	 * @param id  to set the id {@link Long}
	 */

	public void setId(long id) {
		this.id = id;
	}
	/**
	 * setter
	 *
	 * @param name1  to set the name {@link String}
	 */

	public void setName1(String name1) {
		this.name1 = name1;
	}
	/**
	 * setter
	 *
	 * @param price  to set the price {@link Money}
	 */

	public void setPrice(Money price) {
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
	 * @param performanceHour  to set the performancehour {@link String}
	 */
	public void setPerformanceHour(String performanceHour) {
		this.performanceHour = performanceHour;
	}


}