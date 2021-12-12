package festivalmanager.finance;

import org.javamoney.moneta.Money;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class Balance {

	private @Id @GeneratedValue Long id;

	private Money money;

	public Balance(Money money) {this.money=money;}

	public Long getId() {return this.id;}

	public void setId(Long id) {this.id = id;}
}
