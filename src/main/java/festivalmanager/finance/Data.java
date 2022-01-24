package festivalmanager.finance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data")
public class Data
{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    public String name;
    @Column(name = "amount")
    public long amount;
    @Column(name = "price")
    public long price; // in €-cents

    public Data()
    {
        this.name = "";
        this.amount = 0;
        this.price = 0;
    }
    public Data(String name, long amount, long price)
    {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }
}