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
	public String name1;
	public Money price;
	public String stage;
	public String performanceHour;

	public Band() {
	}

	/**
	 * Band constructor
	 *
	 * @param name1
	 * @param price
	 * @param stage
	 * @param performanceHour
	 */
	public Band(String name1, Money price, String stage, String performanceHour) {
		this.setPrice(price);
		this.setName1(name1);
		this.setStage(stage);
		this.setPerformanceHour(performanceHour);
	}


	public String getName1() {
		return name1;
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

	public void setName1(String name1) {
		this.name1 = name1;
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