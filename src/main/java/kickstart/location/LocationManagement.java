package kickstart.location;

import org.javamoney.moneta.Money;
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

}
