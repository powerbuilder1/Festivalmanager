package festivalmanager.personalManagement;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserForm;
import festivalmanager.authentication.UserRepository;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.location.LocationManagement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasRole('BOSS')")
public class ManagerController {
	public final ManagerManagement managerManagement;
	private final UserRepository userRepository;
	private final LocationManagement locationManagement;
	private final FestivalManagement festivalManagement;

	/**
	 * Creates a new {@link ManagerController} with the given {@link ManagerManagement},
	 * {@link UserRepository} , {@link LocationManagement}, {@link FestivalManagement}
	 *
	 * @param managerManagement must not be {@literal null}.
	 * @param userRepository must not be {@literal null}.
	 * @param locationManagement must not be {@literal null}.
	 * @param festivalManagement must not be {@literal null}.
	 */
	public ManagerController(ManagerManagement managerManagement, UserRepository userRepository,
			LocationManagement locationManagement, FestivalManagement festivalManagement) {
		this.managerManagement = managerManagement;
		this.userRepository = userRepository;
		this.locationManagement = locationManagement;
		this.festivalManagement = festivalManagement;
	}
	/**
	 * route to manager's dashboard
	 * @param model
	 * @return
	 */
	@GetMapping("/dashboard")
	String dashboard(Model model) {
		return "dashboard";

	}

	/**
	 * route to the team overview
	 * @param model
	 * @return
	 */
	@GetMapping("/team")
	String team(Model model) {
		model.addAttribute("userList", managerManagement.findAll());
		return "team";
	}
	/**
	 * After filling the {@link UserForm} with valid data , a new user is created
	 * @param form
	 * @param result
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/new_personal")
	String registerNew(@ModelAttribute @Valid UserForm form, Errors result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error",
					result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList()));
			return "redirect:/team";
		}
		if (!managerManagement.findAllByName(form.getName()).isEmpty()) {
			redirectAttributes.addFlashAttribute("errors", "PERSON_ALREADY_EXISTS");
			return "redirect:/team";
		}

		// Falls alles in Ordnung ist legen wir einen Mitarbeiter an
		if (form.getPosition().equalsIgnoreCase("catering")) {
			managerManagement.createCateringStaff(form);
		} else if (form.getPosition().equalsIgnoreCase("planning")) {
			managerManagement.createPlanningStaff(form);
		} else if (form.getPosition().equalsIgnoreCase("festivalleiter")) {
			managerManagement.createFestivalDirector(form);
		} else if (form.getPosition().equalsIgnoreCase("security")) {
			managerManagement.createFestivalDirector(form);
		}
		return "redirect:/team";
	}
	/**
	 * route to create a new user
	 * @param model
	 * @param userForm
	 * @return
	 */
	@GetMapping("/new_personal")
	String register(Model model, UserForm userForm) {
		model.addAttribute("festivals", festivalManagement.findAllFestivals());
		model.addAttribute("locations", locationManagement.findAllLocations().toList());
		return "new_personal";
	}
	/**
	 * route to change user information
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping(path = "dashboard/team/personal_edit/{user}")
	public String getUser(
			@PathVariable("user") Long id,
			Model model) {
		User user = userRepository.findById(id).get();
		model.addAttribute("festivals", festivalManagement.findAllFestivals());
		model.addAttribute("locations", locationManagement.findAllLocations().toList());
		model.addAttribute("user", user);
		model.addAttribute("userForm", new UserForm(user.getName(), user.getPassword(), user.getAddress(),
				user.getPosition(), user.getWorkPlace(), user.getFestival().getId()));
		System.out.println(user.getName());
		System.out.println(user.getPassword());
		System.out.println(user.getAddress());
		System.out.println(user.getPosition());
		System.out.println(user.getWorkPlace());

		return "personal_edit";
	}

	/**
	 * edit user by id from {@link UserRepository}
	 * @param id
	 * @param userForm
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/dashboard/team/editUserById/{user}")
	public String editUserById(
			@PathVariable("user") long id,
			@ModelAttribute UserForm userForm,
			Model model

	) {
		managerManagement.editUser(userRepository.findById(id).get(), userForm);
		return "redirect:/team";
	}

	/**
	 * delete user by id from {@link UserRepository}
	 * @param id
	 * @return
	 */
	@PostMapping(path = "/dashboard/team/deleteUserById/{user}")
	public String deleteUserById(@PathVariable("user") Long id) {
		// remove person from chats
		festivalManagement.getCommunicationManagement().removeEverywhere(userRepository.findById(id).get());
		managerManagement.deleteUser(userRepository.findById(id).get());
		return "redirect:/team";
	}

}
