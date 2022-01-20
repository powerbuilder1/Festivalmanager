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
    public Finance()
    {
        this.id = 0;
        dataMap = new HashMap<String, Data>();
    }

    public Finance(long id) {
        this.id = id;
        dataMap = new HashMap<String, Data>();
    }

    public void addData(String name, long amount, long price)
    {
        if (this.dataMap.get(name) != null)
        {
            this.dataMap.get(name).amount += amount;
            this.dataMap.get(name).price += price;
        }
        else
        {
            this.dataMap.put(name, new Data(name, amount, price));
        }
    }

    public boolean hasData(String name)
    {
        return this.dataMap.get(name) != null;
    }

    public void overwriteData(String name, long amount, long price)
    {
        this.dataMap.put(name, new Data(name, amount, price));
    }

    public Map<String, Data> getFinanceData()
    {
        return this.dataMap;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<String,Data> getDataMap() {
        return this.dataMap;
    }

    public void setDataMap(Map<String,Data> dataMap) {
        this.dataMap = dataMap;
    }

}
