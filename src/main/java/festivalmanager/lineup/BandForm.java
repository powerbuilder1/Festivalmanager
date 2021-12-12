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

	public BandForm(String name, double price, String stage, String performanceHour, Long id) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.performanceHour = performanceHour;
		this.stage = stage;
	}
	public BandForm() {
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

	public double getPrice() {
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

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public void setPerformanceHour(String performanceHour) {
		this.performanceHour = performanceHour;
	}
}



