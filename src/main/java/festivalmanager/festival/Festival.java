package festivalmanager.festival;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import festivalmanager.location.Location;

@Entity
public class Festival {

    private @Id @GeneratedValue Long id;

    @ManyToOne
    private Location location;
    private long locationIdentifier;
    private String beginDate;
    private String endDate;

    // varchar(255) is to small
    @Column(columnDefinition = "TEXT")
    private String information;
    private String name;

    public Festival() {
    }

    /**
     * Festival constructor
     * 
     * @param name
     * @param location
     * @param beginDate
     * @param endDate
     * @param information
     */
    public Festival(String name, Location location, String beginDate, String endDate, String information) {
        this.setName(name);
        this.setLocation(location);
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        this.information = information;
        this.locationIdentifier = location.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public long getLocationIdentifier() {
        return locationIdentifier;
    }

    public void setLocationIdentifier(long locationIdentifier) {
        this.locationIdentifier = locationIdentifier;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", location='" + getLocation() + "'" +
                ", locationIdentifier='" + getLocationIdentifier() + "'" +
                ", beginDate='" + getBeginDate() + "'" +
                ", endDate='" + getEndDate() + "'" +
                ", information='" + getInformation() + "'" +
                ", name='" + getName() + "'" +
                "}";
    }

}