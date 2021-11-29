package festivalmanager.location;

import org.javamoney.moneta.Money;
import org.salespointframework.core.Currencies;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class LocationManagement {

    private final LocationRepository locationRepository;

    public LocationManagement(LocationRepository locationRepository) {
        Assert.notNull(locationRepository, "locationRepository must not be null");
        this.locationRepository = locationRepository;
    }

    /**
     * saves the location in the repository
     * 
     * @param location
     * @return
     */
    public Location createLocation(Location location) {
        Assert.notNull(location, "location must not be null");
        return locationRepository.save(location);
    }

    public Location createLocation(LocationForm form) {
        Assert.notNull(form, "form must not be null");
        return createLocation(form.getName(), form.getMaxVisitors(), form.getMaxStages(),
                Money.of(form.getRent(), Currencies.EURO));
    }

    /**
     * Creates a new location with the given name, visitors, stages and price
     * 
     * @param name
     * @param maxVisitors
     * @param stages
     * @param rent
     * @return
     */
    public Location createLocation(String name, int maxVisitors, int stages, Money rent) {
        Assert.hasText(name, "Name must not be empty");
        Assert.isTrue(maxVisitors > 0, "maxVisitors must be greater than 0");
        Assert.isTrue(stages > 0, "stages must be greater than 0");
        Assert.notNull(rent, "rent must not be null");
        return createLocation(new Location(name, maxVisitors, stages, rent));
    }

    /**
     * updates the given location in the repository
     * 
     * @param location
     * @return
     */
    public Location updateLocation(Location location) {
        Assert.notNull(location, "location must not be null");
        return locationRepository.save(location);
    }

    public Location updateLocation(LocationForm form) {
        Assert.notNull(form, "form must not be null");
        Location location = findById(form.getId());
        location.setMaxVisitors(form.getMaxVisitors());
        location.setMaxStages(form.getMaxStages());
        location.setRent(Money.of(form.getRent(), Currencies.EURO));
        location.setName(form.getName());
        System.out.println(location.getName());
        System.out.println(location.getMaxVisitors());
        System.out.println(location.getMaxStages());
        System.out.println(location.getRent());
        System.out.println(location.getId());
        return updateLocation(location);
    }

    /**
     * returns all Locations
     * 
     * @return
     */
    public Streamable<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    /**
     * returns the locations with the given name
     * 
     * @param name
     * @return
     */
    public Streamable<Location> findAllByName(String name) {
        return locationRepository.findAll().filter(location -> location.getName().equals(name));
    }

    /**
     * returns the location with the given id
     * 
     * @param id
     * @return
     */
    public Location findById(long id) {
        return locationRepository.findById(id).orElse(null);
    }
}