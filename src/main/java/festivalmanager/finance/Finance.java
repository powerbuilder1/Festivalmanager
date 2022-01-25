package festivalmanager.finance;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

/**
 * Finance entry
 */
@Entity
@Table(name = "finance")
public class Finance {

    @Id
    @Column(name = "id")
    private long id;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_item_mapping", 
        joinColumns = {@JoinColumn(name = "finance_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "data_id", referencedColumnName = "id")})
    @MapKey(name = "name")
    private Map<String, Data> dataMap;

    //default constructor
    public Finance() {
        this.id = 0;
        dataMap = new HashMap<String, Data>();
    }

    /**
     * Constructor
     * @param id of the festival and finance
     */
    public Finance(long id) {
        this.id = id;
        dataMap = new HashMap<String, Data>();
    }

    /**
     * Add data to a entry
     * @param name of the data
     * @param amount of the data
     * @param price of the data (per unit)
     */
    public void addData(String name, long amount, long price) {
        if (this.dataMap.get(name) != null) {
            this.dataMap.get(name).amount += amount;
            this.dataMap.get(name).price += price;
        } else {
            this.dataMap.put(name, new Data(name, amount, price));
        }
    }

    /**
     * Returns wether the data entry already exists
     * @param name
     * @return
     */
    public boolean hasData(String name) {
        return this.dataMap.get(name) != null;
    }

    /**
     * Overwrites the data
     * @param name of the data
     * @param amount of the data
     * @param price of the data (per unit)
     */
    public void overwriteData(String name, long amount, long price) {
        this.dataMap.put(name, new Data(name, amount, price));
    }

    /**
     * Returns the data
     * @return {@link Map}<{@link String}, {@link Data}> {Name | Data with this name}
     */
    public Map<String, Data> getFinanceData() {
        return this.dataMap;
    }

    /**
     * Returns the id
     * @return id
     */
    public long getId() {
        return this.id;
    }

    /**
     * Sets the id
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Setter for dataMap
     * @param dataMap
     */
    public void setDataMap(Map<String,Data> dataMap) {
        this.dataMap = dataMap;
    }

}
