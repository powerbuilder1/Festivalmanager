package kickstart.location;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.javamoney.moneta.Money;

@Entity
public class Location {

    private @Id @GeneratedValue Long id;

    private int maxVisitors;
    private int maxStages;
    private String name;
    private Money rent;

    /**
     * Location constructor
     * 
     * @param name
     * @param maxVisitors
     * @param maxStages
     * @param rent
     */
    public Location(String name, int maxVisitors, int maxStages, Money rent) {
        this.setName(name);
        this.setMaxVisitors(maxVisitors);
        this.setMaxStages(maxStages);
        this.setRent(rent);
    }

    public Money getRent() {
        return rent;
    }

    public void setRent(Money rent) {
        this.rent = rent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxStages() {
        return maxStages;
    }

    public void setMaxStages(int maxStages) {
        this.maxStages = maxStages;
    }

    public int getMaxVisitors() {
        return maxVisitors;
    }

    public void setMaxVisitors(int maxVisitors) {
        this.maxVisitors = maxVisitors;
    }

}
