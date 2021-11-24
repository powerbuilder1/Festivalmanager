package festivalmanager.lineup;


import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.location.Location;
import festivalmanager.location.LocationManagement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.sound.sampled.Line;
@Controller
public class LineUpController {
	LineUpManagement lineUpManagement;
	LineUpController(LineUpManagement lineUpManagement) {
		Assert.notNull(lineUpManagement, "lineupManagement must not be null");
		this.lineUpManagement= lineUpManagement;

	}

	@GetMapping("/lineup")
	String lineup(Model model) {
		model.addAttribute("lineup", lineUpManagement.findAllLineUp());
		return "lineup";
	}


	@GetMapping("/lineup/{id}")
	String location(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
		LineUp lineup = lineUpManagement.findById(id);
		if (lineup == null) {
			redirectAttributes.addFlashAttribute("error", "LINEUP_NOT_FOUND");
			return "redirect:/lineup";
		}

		model.addAttribute("lineup", lineup);
		return "lineup";
	}
}