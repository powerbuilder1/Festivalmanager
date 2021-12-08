package festivalmanager.location;

import javax.persistence.Column;
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

    // varchar(255) is to small
    @Column(columnDefinition = "TEXT")
    private String data;
    @Column(columnDefinition = "TEXT")
    private String staticImage;

    public Location() {
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String getStaticImage() {
        return staticImage;
    }

    public void setStaticImage(String staticImage) {
        this.staticImage = staticImage;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", maxVisitors='" + getMaxVisitors() + "'" +
                ", maxStages='" + getMaxStages() + "'" +
                ", name='" + getName() + "'" +
                ", rent='" + getRent() + "'" +
                "}";
    }

}
