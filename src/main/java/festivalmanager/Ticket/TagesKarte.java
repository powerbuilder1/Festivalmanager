package festivalmanager.Ticket;

import org.javamoney.moneta.Money;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class TagesKarte extends Ticket  {

	public TagesKarte() {
	}

	public TagesKarte (Money price, String barcode, double validePeriod) {

		super(price,barcode,validePeriod);

	}

	@Override public void Kaufen() {
	}

}


