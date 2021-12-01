package festivalmanager.lineup;

import org.javamoney.moneta.Money;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Band {
	private @Id
	@GeneratedValue
	long id;
	public String name;
	public Money price;
	public String stage;
	public String performanceHour;

	public Band() {
	}

	/**
	 * Location constructor
	 *
	 * @param name
	 * @param price
	 * @param stage
	 * @param performanceHour
	 */
	public Band(String name, Money price, String stage, String performanceHour) {
		this.setPrice(price);
		this.setName(name);
		this.setStage(stage);
		this.setPerformanceHour(performanceHour);
	}


	public String getName() {
		return name;
	}

	public String getStage() {
		return stage;
	}

	public String getPerformanceHour() {
		return performanceHour;
	}

	public Money getPrice() {
		return price;
	}

	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Money price) {
		this.price = price;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public void setPerformanceHour(String performanceHour) {
		this.performanceHour = performanceHour;
	}


}