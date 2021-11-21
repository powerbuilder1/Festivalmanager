package kickstart.festival;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import kickstart.location.Location;

@Entity
public class Festival {

    private @Id @GeneratedValue Long id;

    private Location location;
    private long locationId;
    private String beginDate;
    private String endDate;

    // varchar(255) is to small
    @Column(columnDefinition = "TEXT")
    private String information;
    private String name;

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

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
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
}
