package festivalmanager.order;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.IOException;
import com.itextpdf.io.source.ByteArrayOutputStream;
import festivalmanager.TicketStock.TicketOrderForm;
import festivalmanager.Ticket.TicketManagement;
import festivalmanager.TicketStock.TicketStockManagement;
import festivalmanager.catering.CateringManagement;
import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.stock.ReorderForm;
import org.salespointframework.order.Cart;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;


@Controller
@PreAuthorize("isAuthenticated()")
@SessionAttributes("cart")

public class OrderController {

	@Autowired
	ServletContext servletContext;
	private final CateringManagement cateringManagement;
	private final TicketManagement ticketManagement;
	private final TicketStockManagement ticketStockManagement;
	private final TicketCustomOrderManagement ticketCustomOrderManagement;
	private final CustomOrderManagement customOrderManagement;
	private final FestivalManagement festivalManagement;
	private final TemplateEngine templateEngine;
	/**
	 * Constructor OrderController
	 * @param cateringManagement
	 * @param customOrderManagement
	 * @param ticketManagement
	 * @param ticketCustomOrderManagement
	 * @param festivalManagement
	 * @param templateEngine
	 * @param ticketStockManagement

	 */
	public OrderController(CateringManagement cateringManagement, CustomOrderManagement customOrderManagement,
			TicketManagement ticketManagement,TicketCustomOrderManagement ticketCustomOrderManagement,
			FestivalManagement festivalManagement,TemplateEngine templateEngine,
			TicketStockManagement ticketStockManagement) {
		this.cateringManagement = cateringManagement;
		this.customOrderManagement = customOrderManagement;
		this.ticketManagement = ticketManagement;
		this.ticketCustomOrderManagement = ticketCustomOrderManagement;
		this.festivalManagement = festivalManagement;
		this.templateEngine = templateEngine;
		this.ticketStockManagement = ticketStockManagement;
	}

	/**
	 * initialize cart of the session
	 * @return
	 */
	@ModelAttribute("cart")
	Cart initializeCart() {
		return new Cart();
	}
	/**
	 * route to template which shows which food items will be sold
	 * @param form
	 * @param model
	 * @param userAccount
	 * @return
	 */
	@PreAuthorize("hasAnyRole('CATERING', 'BOSS')")
	@GetMapping(path = "catering/sale")
	String getCart(
			ReorderForm form,
			Model model,
			@LoggedIn Optional<UserAccount> userAccount
			) {
		model.addAttribute("catalog", cateringManagement.getCatalog(userAccount));
		return "catering";
	}
	/**
	 * route to template which shows which ticket items of a festival will be sold
	 * @param id of the festival chosen
	 * @param model
	 * @param userAccount
	 * @return
	 */
	@PreAuthorize("hasAnyRole('CUSTOMER', 'BOSS')")
	@GetMapping(path = "ticket/{id}/sale")
	String getCartTicket(@PathVariable long id,
			Model model,
			@LoggedIn Optional<UserAccount> userAccount
	) {
		Festival festival = festivalManagement.findById(id);
		model.addAttribute("id",festival.getId());
		model.addAttribute("catalogTicket", ticketManagement.getTicketCatalog(festival.getName()));
		model.addAttribute("TicketorderForm", new TicketOrderForm());
		model.addAttribute("ticketStock", ticketStockManagement.getTicketStockbyfestival(festival.getName()));
		return "ticket_sale";
	}
	/**
	 * add food items of a festival to the cart of the session
	 * @param cart
	 * @param form
	 * @param result
	 * @param model
	 * @param userAccount
	 * @return
	 */
	@PreAuthorize("hasRole('CATERING')")
	@PostMapping(path = "catering/order")
	String addFoodToCard(
			@ModelAttribute Cart cart,
			@Valid ReorderForm form,
			BindingResult result,
			Model model,
			@LoggedIn Optional<UserAccount> userAccount
			) {
		if (result.hasErrors()) {
			model.addAttribute("catalog", cateringManagement.getCatalog(userAccount));
			return "catering";
		}
		customOrderManagement.addFoodToCard(cart, form);

		return "redirect:/catering/sale";
	}
	/**
	 * add ticket items of a festival to the cart of the session
	 * @param cart
	 * @param ticketOrderForm
	 * @param userAccount

	 * @return
	 */
	@PreAuthorize("hasAnyRole('CUSTOMER', 'BOSS')")
	@PostMapping(path = "/ticket/{id}/sale")
	String addtTicketToCart(@PathVariable long id,
			@ModelAttribute Cart cart,
			@ModelAttribute TicketOrderForm ticketOrderForm,
			@LoggedIn Optional<UserAccount> userAccount
	) {

		ticketCustomOrderManagement.addTicketToCart(cart, ticketOrderForm);

		return "redirect:/ticket/"+id+"/sale";
	}
	/**
	 * buy food items of a festival found in the cart of the session
	 * @param cart
	 * @param userAccount
	 * @return
	 */
	@PreAuthorize("hasRole('CATERING')")
	@PostMapping(path = "catering/checkout")
	String buy(
			@ModelAttribute Cart cart,
			@LoggedIn Optional<UserAccount> userAccount
	) {
		return customOrderManagement.buy(cart, userAccount);
	}
	/**
	 * buy ticket items of a festival found in the cart of the session
	 * @param cart
	 * @param userAccount
	 * @return
	 */
	@PreAuthorize("hasAnyRole('CUSTOMER', 'BOSS')")
	@PostMapping(path = "ticket/checkout")
	String buyTicket(
			@ModelAttribute Cart cart,
			@LoggedIn Optional<UserAccount> userAccount

	) {
		System.out.println(userAccount);
		return ticketCustomOrderManagement.buyTicket(cart, userAccount);

	}
	/**
	 * delete all ticket items of a festival found in the cart of the session
	 * @param cart
	 * @param userAccount
	 * @return
	 */
	@PreAuthorize("hasAnyRole('CUSTOMER', 'BOSS')")
	@PostMapping(path = "/ticket/{id}/sale/delete")
	String deleteTicket(
			@PathVariable long id,
			@ModelAttribute Cart cart,
			@LoggedIn Optional<UserAccount> userAccount

	) {
		cart.clear();
		return "redirect:/ticket/"+id+"/sale";
	}
	/**
	 * give an invoice of the selled tickets
	 * @param request
	 * @param response
	 * @param cart
	 * @param userAccount
	 * @return
	 */
	@PreAuthorize("hasAnyRole('CUSTOMER', 'BOSS')")
	@GetMapping(path = "/pdf")
	public ResponseEntity<?> getPDF(HttpServletRequest request, Cart cart, HttpServletResponse response,
		@LoggedIn Optional<UserAccount> userAccount) throws IOException {

		/* Do Business Logic*/
		/* Create HTML using Thymeleaf template Engine */

		WebContext context = new WebContext(request, response, servletContext);
		context.setVariable("cart", cart);
		String [] parts = userAccount.toString().split("[\\(||\\)]" );

		context.setVariable("user2", parts[1]);

		String orderHtml = templateEngine.process("ticketpdf", context);

		/* Setup Source and target I/O streams */

		ByteArrayOutputStream target = new ByteArrayOutputStream();

		/*Setup converter properties. */
		ConverterProperties converterProperties = new ConverterProperties();
		converterProperties.setBaseUri("http://localhost:8080/");

		/* Call convert method */
		HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

		/* extract output as bytes */
		byte[] bytes = target.toByteArray();


		/* Send the response as downloadable PDF */
		cart.clear();
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.body(bytes);

	}




}
