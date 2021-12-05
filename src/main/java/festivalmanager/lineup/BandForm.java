package festivalmanager.lineup;
import org.javamoney.moneta.Money;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class BandForm {

	private final long id;
	private final @NotEmpty String name;
	private final @NotEmpty Money price;
	private final @NotEmpty String stage;
	private final @NotEmpty String performanceHour;

	public BandForm(String name, Money price, String stage, String performanceHour, Long id) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.performanceHour = performanceHour;
		this.stage = stage;
	}


	public String getName() {
		return this.name;
	}

	public String getStage() {
		return this.stage;
	}

	public String getPerformanceHour() {
		return this.performanceHour;
	}

	public Money getPrice() {
		return this.price;
	}

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
}



