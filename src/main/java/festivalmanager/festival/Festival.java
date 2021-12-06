package festivalmanager.festival;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

import festivalmanager.catering.Food;
import festivalmanager.location.Location;

import java.util.Set;

@Entity
@FestivalConstraint()
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
    @NotNull
    private String name;

    @OneToMany(mappedBy = "festival")
    private Set<Food> catalogFoodItems;

    private boolean isPublished;

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
        this.isPublished = false;
    }

    /**
     * Festival constructor
     * 
     * @param name
     * @param location
     * @param beginDate
     * @param endDate
     * @param information
     * @param isPublished
     */
    public Festival(String name, Location location, String beginDate, String endDate, String information,
            boolean isPublished) {
        this.setName(name);
        this.setLocation(location);
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        this.information = information;
        this.locationIdentifier = location.getId();
        this.isPublished = isPublished;
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

    public boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
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