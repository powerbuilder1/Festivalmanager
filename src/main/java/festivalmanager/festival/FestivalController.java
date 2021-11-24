package festivalmanager.festival;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FestivalController {

    FestivalManagement festivalManagement;

    FestivalController(FestivalManagement festivalManagement) {
        Assert.notNull(festivalManagement, "festivalManagement must not be null");
        this.festivalManagement = festivalManagement;
    }

    @GetMapping("/")
    String festivals(Model model) {
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

}