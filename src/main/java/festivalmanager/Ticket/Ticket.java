package festivalmanager.Ticket;

import festivalmanager.festival.Festival;
import org.javamoney.moneta.Money;

import javax.persistence.*;

@Entity
public abstract class Ticket {
	@ManyToOne
	private Festival festival;
	private @Id long id;
	public Money price;
	public String  barcode;
	public double validePeriod;

	public Ticket(Festival festival, Money price, String barcode, double validePeriod) {
		this.festival = festival;

		this.setId(festival.getId());
		this.price = price;
		this.barcode = barcode;
		this.validePeriod = validePeriod;
	}
	public Ticket() {
	}
	public long getId() {
		return id;
	}
	public Money getPrice() {
		return price;
	}
	public String getBarcode() {
		return barcode;
	}
	public double getValidePeriod() {
		return validePeriod;
	}

	public abstract void Kaufen();

	public void setFestival(Festival festival) {
		this.festival = festival;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPrice(Money price) {
		this.price = price;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setValidePeriod(double validePeriod) {
		this.validePeriod = validePeriod;
	}
}



