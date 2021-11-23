package kickstart.location;

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

}
