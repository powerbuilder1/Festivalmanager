package festivalmanager.lineup;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.sound.sampled.Line;
@Controller
public class LineUpController {
	LineUpRepository LineUpRepository;
	LineUpController(LineUpRepository LineUpRepository) {
		Assert.notNull(LineUpRepository, "LineUpRepository must not be null");
		this.LineUpRepository= LineUpRepository;
	}

	@GetMapping("/lineup")
	String lineup(Model model) {
		model.addAttribute("lineup", LineUpRepository.findAll());
		model.addAttribute("title", "lineup");
		return "lineup";

	}
}