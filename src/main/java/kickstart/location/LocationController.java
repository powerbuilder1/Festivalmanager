package kickstart.location;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

@Controller
public class LocationController {

    LocationManagement locationManagement;

    public LocationController(LocationManagement locationManagement) {
        Assert.notNull(locationManagement, "locationManagement must not be null");
        this.locationManagement = locationManagement;
    }

}
