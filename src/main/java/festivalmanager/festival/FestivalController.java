package festivalmanager.festival;

import java.util.stream.Collectors;

import javax.validation.Valid;

import festivalmanager.lineup.LineUp;
import festivalmanager.lineup.LineUpManagement;

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

import festivalmanager.location.LocationManagement;

/**
 * Controller for the festival.
 *
 */
@Controller
public class FestivalController {

    FestivalManagement festivalManagement;
    LocationManagement locationManagement;
    LineUpManagement lineUpManagement;

    /**
     * Constructor for the FestivalController.
     * @param festivalManagement
     * @param locationManagement
     * @param lineUpManagement
     */
    FestivalController(FestivalManagement festivalManagement, LocationManagement locationManagement,
            LineUpManagement lineUpManagement) {
        Assert.notNull(festivalManagement, "festivalManagement must not be null");
        Assert.notNull(locationManagement, "locationManagement must not be null");
        this.festivalManagement = festivalManagement;
        this.locationManagement = locationManagement;
        this.lineUpManagement = lineUpManagement;
    }


/**
 * Returns html template with all published festivals
 * @param model
 * @return string of html template
 */
    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("festivals", festivalManagement.findAllPublishedFestivals());
        model.addAttribute("title", "Festivals");
        return "index";
    }

    /**
     * Publishes a festival
     * @param id of the festival
     * @param redirectAttributes
     * @return
     */
    @PreAuthorize("hasAnyRole('BOSS', 'PLANNING')")
    @GetMapping("/publish/{id}")
    String publishFestival(@PathVariable long id, RedirectAttributes redirectAttributes) {
        String error = festivalManagement.publishById(id);
        if (error != "ok") {
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:/festival/" + id;
        }
        return "redirect:/festival";
    }

    /**
     * Returns html template with the selected festival
     * @param id of the festival
     * @param model
     * @param redirectAttributes
     * @return string of html template
     */
    @GetMapping("/festival/{id}")
    String festival(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        Festival festival = festivalManagement.findById(id);
        LineUp lineup = lineUpManagement.findById(id);
        System.out.println("lel");
        if (lineup == null) {
            redirectAttributes.addFlashAttribute("error", "LINEUP_NOT_FOUND");
            return "redirect:/lineup";
        }
        if (festival == null) {
            redirectAttributes.addFlashAttribute("error", "FESTIVAL_NOT_FOUND");
            return "redirect:/";
        }
        model.addAttribute("lineup", lineup);
        model.addAttribute("festival", festival);
        model.addAttribute("title", festival.getName());

        return "festival";
    }

    /**
     * Returns html template with all festivals
     * @param model
     * @return
     */
    @PreAuthorize("hasAnyRole('BOSS', 'PLANNING')")
    @GetMapping("/festival")
    String festivals(Model model) {
        model.addAttribute("festivals", festivalManagement.findAllFestivals());
        model.addAttribute("title", "Festivals");
        return "festivals";
    }

    /**
     * Returns html template for creating a new festival
     * @param model
     * @return
     */
    @PreAuthorize("hasAnyRole('BOSS', 'PLANNING')")
    @GetMapping("/festival/new")
    String newFestival(Model model) {
        model.addAttribute("festival", new Festival());
        model.addAttribute("title", "New Festival");
        model.addAttribute("locations", locationManagement.findAllLocations());
        return "festival_new";
    }

    /**
     * Post mapping for creating a new festival
     * @param festival to be created
     * @param result Errors of the form
     * @param redirectAttributes
     * @return string of html template
     */
    @PreAuthorize("hasAnyRole('BOSS', 'PLANNING')")
    @PostMapping("/festival/new")
    String newFestival(@ModelAttribute @Valid Festival festival, Errors result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors",
                    result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList()));
            return "redirect:/festival";
        }
        if (!festivalManagement.findAllByName(festival.getName()).isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "FESTIVAL_ALREADY_EXISTS");
            return "redirect:/festival";
        }

        festivalManagement.createFestival(festival);
        return "redirect:/festival";
    }

    /**
     * Returns html template for editing a festival
     * @param id of the festival
     * @param model 
     * @param redirectAttributes
     * @return string of html template
     */
    @PreAuthorize("hasAnyRole('BOSS', 'PLANNING')")
    @GetMapping("/festival/{id}/edit")
    String editFestival(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        Festival festival = festivalManagement.findById(id);
        if (festival == null) {
            redirectAttributes.addFlashAttribute("error", "FESTIVAL_NOT_FOUND");
            return "redirect:/festival";
        }

        model.addAttribute("festival", festival);
        return "festival_edit";
    }

    /**
     * Post mapping for editing a festival
     * @param id of the festival
     * @param festival to be edited
     * @param result Errors of the form
     * @param redirectAttributes
     * @return string of html template
     */
    @PreAuthorize("hasAnyRole('BOSS', 'PLANNING')")
    @PostMapping("/festival/{id}/edit")
    String editFestival(@PathVariable long id, @ModelAttribute @Valid Festival festival, Errors result,
            RedirectAttributes redirectAttributes) {
        System.out.println(festival.toString());
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.toString());
            return "redirect:/festival/" + id + "/edit";
        }
        festival.setId(id);
        festivalManagement.updateFestival(festival);
        return "redirect:/festival/" + id;
    }

    /**
     * Returns html template for deleting a festival
     * @param id
     * @param redirectAttributes
     * @return
     */
    @PreAuthorize("hasAnyRole('BOSS', 'PLANNING')")
    @GetMapping("/festival/{id}/delete")
    String deleteFestival(@PathVariable long id, RedirectAttributes redirectAttributes) {
        if (!festivalManagement.deleteById(id)) {
            redirectAttributes.addFlashAttribute("error", "FESTIVAL_NOT_FOUND");
            return "redirect:/festival/" + id;
        }
        return "redirect:/festival";
    }
}