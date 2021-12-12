package festivalmanager.finance;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.javamoney.moneta.Money;

public class Finance {

	private @Id @GeneratedValue Long id;

	//private String name;
	private Money balance;

	public Finance(/*String name,*/ Money balance){
	//	this.name=name;
		this.balance=balance;
	}

	public Long getId() {return this.id;}

	public void setId(Long id) {this.id = id;}

	//public String getName(){return name;}

	public Money getBalance(){return balance;}

	public void setBalance(Money balance){this.balance = balance;}


}
