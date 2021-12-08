package festivalmanager.Ticket;

import org.javamoney.moneta.Money;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public abstract class Ticket {
	private @Id @GeneratedValue long id;
	public Money price;
	public String  barcode;
	public double validePeriod;

	public Ticket(Money price, String barcode, double validePeriod) {
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

}



