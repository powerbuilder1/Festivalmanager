package festivalmanager.Ticket;

import festivalmanager.festival.FestivalManagement;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class TicketController  {
	private TicketRepository ticketRepository;
	private TicketManagement ticketManagement;
	private FestivalManagement festivalManagement;
	/**
	 * Controller
	 * @param ticketManagement
	 * @param ticketRepository
	 * @param festivalManagement

	 */
	public TicketController( TicketRepository ticketRepository, 
		TicketManagement ticketManagement, FestivalManagement festivalManagement) {

		this.ticketManagement = ticketManagement;
		this.ticketRepository = ticketRepository;
		this.festivalManagement = festivalManagement;
	}

	/**
	 * route to overview of a page where the manager can add tickets to an specific festival
	 * @param model
	 * @return
	 */

	@PreAuthorize("hasRole('BOSS')")
	@GetMapping(path = "ticket/new")
	public String getNewTickets(Model model) {
		model.addAttribute("TicketForm", new TicketForm());
		model.addAttribute("festivals", festivalManagement.findAllFestivals());
		model.addAttribute("ticketType1", Ticket.TicketType.CAMPINGTICKET );
		model.addAttribute("ticketType2", Ticket.TicketType.TAGESKARTE );
		model.addAttribute("ticketType3", Ticket.TicketType.ABENDKASSE );

		return "ticket_new";
	}
	/**
	 * add ticket to a Festival
	 * @param ticketForm
	 * @param result
	 * @param redirectAttributes
	 * @param account

	 * @return
	 */
	@PreAuthorize("hasRole('BOSS')")
	@PostMapping(path = "ticket/addTicket")
	public String addTicketToCatalog(
			@Valid @ModelAttribute TicketForm ticketForm,
			Errors result,
			RedirectAttributes redirectAttributes,
			@LoggedIn Optional<UserAccount> account
	) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", result.toString());
			return "redirect:/ticket/new";
		}
		try {
			ticketManagement.addTicketToCatalog(ticketForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}





}
