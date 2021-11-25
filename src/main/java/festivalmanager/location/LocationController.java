package festivalmanager.location;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @PreAuthorize("hasRole('PLANNING')")
    @GetMapping("/locations")
    String locations(Model model) {
        model.addAttribute("locations", locationManagement.findAllLocations());
        return "locations";
    }

    @PreAuthorize("hasRole('PLANNING')")
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

    @PreAuthorize("hasRole('PLANNING')")
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

    @PreAuthorize("hasRole('PLANNING')")
    @GetMapping("/location/new")
    String newLocation(Model model) {
        model.addAttribute("location", new Location());
        return "location_new";
    }

    @PreAuthorize("hasRole('PLANNING')")
    @PostMapping("/location/new")
    String newLocation(@ModelAttribute LocationForm form, Errors result) {
        if (result.hasErrors()) {
            return "locations";
        }
        locationManagement.createLocation(form);
        return "redirect:/locations";
    }

}
