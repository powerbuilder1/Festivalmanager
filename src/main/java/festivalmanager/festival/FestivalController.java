package festivalmanager.festival;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import festivalmanager.location.Location;
import festivalmanager.location.LocationManagement;

@Controller
public class FestivalController {

    FestivalManagement festivalManagement;
    LocationManagement locationManagement;

    FestivalController(FestivalManagement festivalManagement, LocationManagement locationManagement) {
        Assert.notNull(festivalManagement, "festivalManagement must not be null");
        Assert.notNull(locationManagement, "locationManagement must not be null");
        this.festivalManagement = festivalManagement;
        this.locationManagement = locationManagement;
    }

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("festivals", festivalManagement.findAllFestivals());
        model.addAttribute("title", "Festivals");
        return "index";
    }

    @GetMapping("/festival/{id}")
    String festival(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        Festival festival = festivalManagement.findById(id);
        if (festival == null) {
            redirectAttributes.addFlashAttribute("error", "FESTIVAL_NOT_FOUND");
            return "redirect:/";
        }

        model.addAttribute("festival", festival);
        model.addAttribute("title", festival.getName());

        return "festival";
    }

    @PreAuthorize("hasRole('PLANNING')")
    @GetMapping("/festival")
    String festivals(Model model) {
        model.addAttribute("festivals", festivalManagement.findAllFestivals());
        model.addAttribute("title", "Festivals");
        return "festivals";
    }

    @PreAuthorize("hasRole('PLANNING')")
    @GetMapping("/festival/new")
    String newFestival(Model model) {
        model.addAttribute("festival", new Festival());
        model.addAttribute("title", "New Festival");
        model.addAttribute("locations", locationManagement.findAllLocations());
        return "festival_new";
    }

    @PreAuthorize("hasRole('PLANNING')")
    @PostMapping("/festival/new")
    String newFestival(@ModelAttribute @Valid Festival festival, Errors result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.toString());
            return "redirect:/festival";
        }
        festivalManagement.createFestival(festival);
        return "redirect:/festival";
    }

    @PreAuthorize("hasRole('PLANNING')")
    @GetMapping("/festival/{id}/edit")
    String editFestival() {
        return "festival_edit";
    }

    @PreAuthorize("hasRole('PLANNING')")
    @PostMapping("/festival/{id}/edit")
    String editFestival(int dummy) {
        return "festival_edit";
    }

    @PreAuthorize("hasRole('PLANNING')")
    @GetMapping("/festival/{id}/delete")
    String deleteFestival() {
        return "festival_delete";
    }

}