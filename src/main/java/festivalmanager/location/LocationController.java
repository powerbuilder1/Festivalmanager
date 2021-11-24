package festivalmanager.location;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocationController {

    LocationManagement locationManagement;

    public LocationController(LocationManagement locationManagement) {
        Assert.notNull(locationManagement, "locationManagement must not be null");
        this.locationManagement = locationManagement;
    }

    // @PreAuthorize("hasRolle('Planung')")
    @GetMapping("/locations")
    String locations(Model model) {
        model.addAttribute("locations", locationManagement.findAllLocations());
        return "locations";
    }

}
