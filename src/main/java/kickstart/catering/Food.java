package kickstart.catering;

import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;

import javax.persistence.Entity;

@Entity
public class Food extends Product {
	public Food(String name, Money price) {
		super(name, price);
	}

	public Food() {

	}
}
