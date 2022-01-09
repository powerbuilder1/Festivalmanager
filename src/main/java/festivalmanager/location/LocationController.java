package festivalmanager.location;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        // return "location_edit_image";
        return "location_edit_locationmap";
    }

    @PreAuthorize("hasRole('PLANNING')")
    @GetMapping("/location/{id}/edit")
    String editLocation(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        Location location = locationManagement.findById(id);
        if (location == null) {
            redirectAttributes.addFlashAttribute("error", "LOCATION_NOT_FOUND");
            return "redirect:/locations";
        }

        model.addAttribute("location", location);
        model.addAttribute("rent_value", location.getRent().toString().split(" ")[1]);
        return "location_edit";
    }

    @PreAuthorize("hasRole('PLANNING')")
    @PostMapping("/location/{id}/edit")
    String editLocation(@PathVariable long id, @ModelAttribute @Valid LocationForm form, Errors result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.toString());
            System.out.println("ERROR update location:");
            System.out.println("Error count: " + result.getErrorCount());
            System.out.println(result.toString());
            return "redirect:/location/" + id + "/edit";
        }
        locationManagement.updateLocation(form);
        return "redirect:/location/" + id + "/";
    }

    @PreAuthorize("hasRole('PLANNING')")
    @GetMapping("/location/new")
    String newLocation(Model model) {
        model.addAttribute("location", new Location());
        return "location_new";

    }

    @PreAuthorize("hasRole('PLANNING')")
    @PostMapping("/location/new")
    String newLocation(@ModelAttribute("location") LocationForm form, Errors result,
            RedirectAttributes redirectAttributes) {
        System.out.println(form.toString());
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.toString());
            return "redirect:/locations";
        }
        locationManagement.createLocation(form);
        return "redirect:/locations";
    }

    @PreAuthorize("hasRole('PLANNING')")
    @GetMapping("/location/{id}/delete")
    String deleteLocation(@PathVariable long id, RedirectAttributes redirectAttributes) {
        if (!locationManagement.deleteById(id)) {
            redirectAttributes.addFlashAttribute("error", "LOCATION_HAS_FESTIVALS");
            return "redirect:/location/" + id;
        }
        return "redirect:/locations";
    }

    @PreAuthorize("hasRole('PLANNING')")
    @PostMapping("/location/{id}/locationmap")
    String locationMap(@PathVariable long id, @RequestParam String data, @RequestParam String staticLink) {
        Location location = locationManagement.findById(id);
        location.setData(data);
        location.setStaticImage(staticLink);
        locationManagement.updateLocation(location);
        return "redirect:/location/" + id;
    }

}
