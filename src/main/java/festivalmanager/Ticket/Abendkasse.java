package festivalmanager.Ticket;

import festivalmanager.festival.Festival;
import org.javamoney.moneta.Money;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Abendkasse extends Ticket {
	public Abendkasse() {
	}
	public Abendkasse(Festival festival,Money price, String barcode, double validePeriod) {
		super(festival,price,barcode,validePeriod);
	}
	@Override
	public void Kaufen() {

	}
}
