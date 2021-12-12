package festivalmanager.finance;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.javamoney.moneta.Money;

@Entity
public class Finance {

	private @Id @GeneratedValue Long id;

	private Money balance;

	public Finance(Money balance){
		this.balance=balance;
	}

	public Long getId() {return this.id;}

	public void setId(Long id) {this.id = id;}

	public Money getBalance(){return balance;}

	public void setBalance(Money balance){this.balance = balance;}


}
