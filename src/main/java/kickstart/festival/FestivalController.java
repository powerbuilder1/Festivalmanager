package kickstart.festival;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;

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

}
