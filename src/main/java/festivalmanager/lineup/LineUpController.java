package festivalmanager.lineup;


import festivalmanager.authentication.UserForm;
import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.location.Location;
import festivalmanager.location.LocationManagement;
import org.javamoney.moneta.Money;
import org.salespointframework.order.Cart;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.sound.sampled.Line;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Controller
public class LineUpController {
	LineUpManagement lineUpManagement;

	FestivalManagement festivalManagement;

	BandForm bandForm = new BandForm();

	LineUpController(LineUpManagement lineUpManagement, FestivalManagement festivalManagement) {
		Assert.notNull(lineUpManagement, "lineupManagement must not be null");
		this.lineUpManagement = lineUpManagement;
		this.festivalManagement = festivalManagement;
	}

	@PreAuthorize("hasRole('PLANNING')")
	@GetMapping("/lineup/edit")
	String lineup(Model model) {
		model.addAttribute("lineup", lineUpManagement.findAllLineUp());
		return "lineup_edit";
	}

	@PreAuthorize("hasRole('PLANNING')")
	@GetMapping("/lineup/{id}/add")
	String addbandToLineUp(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
		LineUp lineUp = lineUpManagement.findById(id);
		if (lineUp == null) {
			redirectAttributes.addFlashAttribute("error", "LINEUP_NOT_FOUND");
			return "redirect:/festivals";
		}

		model.addAttribute("lineup", lineUp);
		model.addAttribute("bandform", new BandForm());

		return "lineup_id_add";
	}
	@PreAuthorize("hasRole('PLANNING')")
	@GetMapping("/lineup/{id}/delete")
	String deleteBandfromLineUp (@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
		LineUp lineUp = lineUpManagement.findById(id);
		if (lineUp == null) {
			redirectAttributes.addFlashAttribute("error", "LINEUP_NOT_FOUND");
			return "redirect:/festivals";
		}

		model.addAttribute("lineup", lineUp);
		model.addAttribute("banddelete", new Band());
		return "lineup_id_delete";
	}
	@PreAuthorize("hasRole('PLANNING')")
	@GetMapping("/lineup/{id}/edit")
	String editBandinLineUp (@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
		LineUp lineUp = lineUpManagement.findById(id);
		if (lineUp == null) {
			redirectAttributes.addFlashAttribute("error", "LINEUP_NOT_FOUND");
			return "redirect:/festivals";
		}

		model.addAttribute("lineup", lineUp);
		model.addAttribute("bandname", bandForm);





		return "lineup_id_edit";
	}
	@PreAuthorize("hasRole('PLANNING')")
	@GetMapping("/lineup/{id}/edit/selectname")
	String putValuesineditBandinLineUp (@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
		LineUp lineUp = lineUpManagement.findById(id);
		if (lineUp == null) {
			redirectAttributes.addFlashAttribute("error", "LINEUP_NOT_FOUND");
			return "redirect:/festivals";
		}

		model.addAttribute("lineup", lineUp);
		model.addAttribute("bandname", new Band());




		return "lineup_id_edit_selectname";
	}

	@PreAuthorize("hasRole('PLANNING')")
	@GetMapping("/lineup/new")
	String newLineUp(Model model) {
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
		try {
			lineUpManagement.createLineUp(lineup);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/lineup/edit";
	}

	@PreAuthorize("hasRole('PLANNING')")
	@PostMapping("/lineup/{id}/addband")
	public String addBand(@PathVariable long id, @ModelAttribute BandForm bandform) {

		System.out.println(bandform.getName());
		lineUpManagement.addBand(id,bandform);

		return "redirect:/lineup/"+id+"/add";

	}

	@PreAuthorize("hasRole('PLANNING')")
	@PostMapping("/lineup/{id}/deleteband")
	public String deleteBand(@PathVariable long id, @RequestParam String name1) {

		System.out.println(name1);
		lineUpManagement.deleteBand(id,name1);

		return "redirect:/lineup/"+id+"/delete";

	}

	@PreAuthorize("hasRole('PLANNING')")
	@PostMapping("/lineup/{id}/editband")
	public String editBand(@PathVariable long id, @ModelAttribute BandForm form) {
		System.out.println(form.getName());

		lineUpManagement.updateBand (id,form);

		return "redirect:/lineup/"+id+"/edit/selectname";

	}

	@PreAuthorize("hasRole('PLANNING')")
	@PostMapping("/lineup/{id}/editband/selectname")
	public String putvaluesBand(@PathVariable long id,@RequestParam String name1) {

		Band foundBand = lineUpManagement.findBandByName(id, name1);
		bandForm.setName(foundBand.getName1());
		bandForm.setStage(foundBand.getStage());
		bandForm.setPrice(foundBand.getPrice().getNumber().doubleValueExact());
		bandForm.setPerformanceHour(foundBand.getPerformanceHour());


		return "redirect:/lineup/"+id+"/edit";

	}

}


