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

	public Band(String name, Money price, String stage, String performanceHour) {
		this.price = price;
		this.name = name;
		this.stage = stage;
		this.performanceHour = performanceHour;
	}

}