package festivalmanager.festival;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

import festivalmanager.Ticket.Ticket;
import festivalmanager.authentication.User;
import festivalmanager.catering.Food;
import festivalmanager.location.Location;
import festivalmanager.stock.FoodInventoryItem;

import java.util.Set;

/**
 * @author Conrad, Philipp, Franz, Aleksey
 * A class to represent a festival
 */
@Entity
@FestivalConstraint()
public class Festival {

    /**
     * The id of the festival (primary key) (auto-generated) {@link Long}
     */
    private @Id @GeneratedValue Long id;

    /**
     * the location of the festival {@link Location}
     */
    @ManyToOne
    private Location location;
    /**
     * the location identifier {@link Long}
     */
    private long locationIdentifier;
    /**
     * the begin date of the festival {@link String}
     */
    private String beginDate;
    /**
     * the end date of the festival {@link String}
     */
    private String endDate;

    /**
     * the information of the festival {@link String}
     */
    // varchar(255) is to small
    @Column(columnDefinition = "TEXT")
    private String information;
    /**
     * the name of the festival {@link String}
     */
    @NotNull
    private String name;

    /**
     * the Food items of the festival {@link Set<FoodInventoryItem>}
     */
    @OneToMany(mappedBy = "festival")
    private Set<Food> catalogFoodItems;

    /**
     * the tickets of the festival {@link Set<Ticket>}
     */
    @OneToMany(mappedBy = "festival")
    private Set<Ticket> tickets;

    /**
     * the food inventory items of the festival {@link Set<FoodInventoryItem>}
     */
    @OneToMany(mappedBy = "festival")
    private Set<FoodInventoryItem> inventoryFoodItems;

    /**
     * the users that are assigned to the festival {@link Set<User>}
     */
    @OneToMany(mappedBy = "festival")
    private Set<User> users;

    /**
     * whether the festival is published or not {@link Boolean}
     */
    private boolean isPublished;

    /**
     * default constructor
     */
    public Festival() {
        // default constructor
    }

    /**
     * Festival constructor
     * 
     * @param name the name of the festival {@link String}
     * @param location the location of the festival {@link Location}
     * @param beginDate the begin date of the festival {@link String}
     * @param endDate the end date of the festival {@link String}
     * @param information the information of the festival {@link String}
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
     * @param name the name of the festival {@link String}
     * @param location the location of the festival {@link Location}
     * @param beginDate the begin date of the festival {@link String}
     * @param endDate the end date of the festival {@link String}
     * @param information the information of the festival {@link String}
     * @param isPublished whether the festival is published or not {@link Boolean}
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

    /**
     * getter
     * 
     * @return the id {@link Long}
     */
    public String getName() {
        return name;
    }

    /**
     * setter
     * 
     * @param name the name to set {@link String}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter
     * 
     * @return the location {@link Location}
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * setter
     * 
     * @param endDate the end date to set {@link String}
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * getter
     * 
     * @return the location {@link Location}
     */
    public String getBeginDate() {
        return beginDate;
    }

    /**
     * setter
     * 
     * @param beginDate the begin date to set {@link String}
     */
    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * getter
     * 
     * @return the location identifier {@link Long}
     */
    public long getLocationIdentifier() {
        return locationIdentifier;
    }

    /**
     * setter
     * 
     * @param locationIdentifier the location identifier to set {@link Long}
     */
    public void setLocationIdentifier(long locationIdentifier) {
        this.locationIdentifier = locationIdentifier;
    }

    /**
     * getter
     * 
     * @return the location {@link Location}
     */
    public Location getLocation() {
        return location;
    }

    /**
     * setter
     * 
     * @param location the location to set {@link Location}
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * getter
     * 
     * @return the information {@link String}
     */
    public String getInformation() {
        return information;
    }

    /**
     * setter
     * 
     * @param information the information to set {@link String}
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * getter
     * 
     * @return the id {@link Long}
     */
    public Long getId() {
        return id;
    }

    /**
     * setter
     * 
     * @param id the id to set {@link Long}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter
     * 
     * @return whether the festival is published or not {@link Boolean}
     */
    public boolean getIsPublished() {
        return isPublished;
    }

    /**
     * setter
     * 
     * @param isPublished whether the festival is published or not {@link Boolean}
     */
    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    /**
     * getter
     * 
     * @return the catalogFoodItems {@link Set<FoodInventoryItem>}
     */
    public Set<Food> getCatalogFoodItems() {
        return catalogFoodItems;
    }

    /**
     * getter
     * 
     * @return the tickets {@link Set<Ticket>}
     */
    public Set<Ticket> getTickets() {
        return tickets;
    }


    /**
     * getter
     * 
     * @return the inventoryFoodItems {@link Set<FoodInventoryItem>}
     */
    public Set<FoodInventoryItem> getInventoryFoodItems() {
        return inventoryFoodItems;
    }

    /**
     * getter
     * 
     * @return the users {@link Set<User>}
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * toString method
     * @return the festival as string representation {@link String}
     */
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