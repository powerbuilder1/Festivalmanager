package festivalmanager.lineup;


import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.location.Location;
import festivalmanager.location.LocationManagement;
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

import javax.sound.sampled.Line;
import javax.validation.Valid;

@Controller
public class LineUpController {
	LineUpManagement lineUpManagement;

	FestivalManagement festivalManagement;
	LineUpController(LineUpManagement lineUpManagement,FestivalManagement festivalManagement) {
		Assert.notNull(lineUpManagement, "lineupManagement must not be null");
		this.lineUpManagement= lineUpManagement;
		this.festivalManagement = festivalManagement;
	}

	@PreAuthorize("hasRole('PLANNING')")
	@GetMapping("/lineup/edit")
	String lineup(Model model) {
		model.addAttribute("lineup", lineUpManagement.findAllLineUp());
		return "lineup_edit";
	}


	@GetMapping("/lineup/{id}")
	String lineupId(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
		LineUp lineup = lineUpManagement.findById(id);
		if (lineup == null) {
			redirectAttributes.addFlashAttribute("error", "LINEUP_NOT_FOUND");
			return "redirect:/lineup";
		}

		model.addAttribute("lineup", lineup);
		return "lineup";
	}
	@PreAuthorize("hasRole('PLANNING')")
	@GetMapping("/lineup/{id}/edit")
	String editLineUp(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
		LineUp lineUp = lineUpManagement.findById(id);
		if (lineUp == null) {
			redirectAttributes.addFlashAttribute("error", "LINEUP_NOT_FOUND");
			return "redirect:/festivals";
		}

		model.addAttribute("lineup", lineUp);
		return "lineup_id_edit";
	}

	@PreAuthorize("hasRole('PLANNING')")
	@GetMapping("/lineup/new")
	String newLineUp( Model model) {
		model.addAttribute("lineup", new LineUp());
		model.addAttribute("title", "New LineUp");
		model.addAttribute("festivals", festivalManagement.findAllFestivals());

		return "lineup_new";
		}
	@PreAuthorize("hasRole('PLANNING')")
	@PostMapping("/lineup/new")
	String newLineUp(@ModelAttribute LineUp lineup, Errors result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", result.toString());
			return "redirect:/lineup/edit";
		}
		lineUpManagement.createLineUp(lineup);
		return "redirect:/lineup/edit";
	}
	@PreAuthorize("hasRole('PLANNING')")
	@GetMapping("/band/new")
	String newBand( Model model) {
		model.addAttribute("Band", new Band());
		model.addAttribute("title", "New Band");
		model.addAttribute("Band", lineUpManagement.findAllBands());

		return "band_new";
	}
	@PreAuthorize("hasRole('PLANNING')")
	@PostMapping("/band/new")
	String newBand(@ModelAttribute Band band, Errors result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", result.toString());
			return "redirect:/band/new";
		}

		return "redirect:/band/new";
	}
}