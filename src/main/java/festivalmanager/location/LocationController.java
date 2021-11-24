package festivalmanager.location;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/location/{id}")
    String location(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        Location location = locationManagement.findById(id);
        if (location == null) {
            redirectAttributes.addFlashAttribute("error", "LOCATION_NOT_FOUND");
            return "redirect:/locations";
        }

        model.addAttribute("location", location);
        return "location";
    }

    @GetMapping("/location/{id}/edit/image")
    String editImage(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        Location location = locationManagement.findById(id);
        if (location == null) {
            redirectAttributes.addFlashAttribute("error", "LOCATION_NOT_FOUND");
            return "redirect:/locations";
        }

        model.addAttribute("location", location);
        return "location_edit_image";
    }

}
