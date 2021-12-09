package festivalmanager.Ticket;

import festivalmanager.festival.Festival;
import org.javamoney.moneta.Money;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class TagesKarte extends Ticket  {

	public TagesKarte (Festival festival,Money price, String barcode, double validePeriod) {

		super(festival,price,barcode,validePeriod);

	}

	public TagesKarte() {

	}

	@Override public void Kaufen() {
	}

}


