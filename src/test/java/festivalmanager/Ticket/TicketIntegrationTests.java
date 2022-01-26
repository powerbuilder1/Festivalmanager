package festivalmanager.Ticket;


import static org.assertj.core.api.Assertions.*;

import static org.salespointframework.core.Currencies.*;

import festivalmanager.TicketStock.TicketInventoryItem;
import festivalmanager.TicketStock.TicketStockManagement;
import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.salespointframework.quantity.Quantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Streamable;

import festivalmanager.location.Location;
import festivalmanager.location.LocationManagement;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketIntegrationTests {

	@Autowired
	private FestivalManagement festivalManagement;

	@Autowired
	private LocationManagement locationManagement;

	@Autowired
	private TicketManagement ticketManagement;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TicketStockManagement ticketStockManagement;


	@Test
	void findsAllTickets() {
		Streamable<Ticket> tickets = ticketManagement.getAllTicketCatalog();
		assertThat(tickets).hasSizeGreaterThan(-1);
	}
	@Test
	void addtickettoafestival() {
		// create test entries
		Location location1 = locationManagement.createLocation("TestTickets", 400, 15, Money.of(1500, EURO));
		Festival festival1 = festivalManagement.createFestival("TestTickets", location1, "2022-10-10", "2022-11-11",
				"Test Information");
		Ticket ticket =new Ticket("Abendkasse", Money.of(10, EURO), Ticket.TicketType.ABENDKASSE,
				festivalManagement.findAllByName("TestTickets").toList().get(0), 30);
		ticketRepository.save(ticket);

		Streamable<Ticket> tickets = ticketManagement.getTicketCatalog(festival1.getName());
		System.out.println(tickets.toList().size());
		assertThat(tickets.toList().get(0).equals(ticket));
		Ticket result = tickets.toList().get(0);

		assertThat(result.getName()).isEqualTo(ticket.getName());
		assertThat(result.getFestival().equals(ticket.getFestival()));
		assertThat(result.getType()).isEqualTo(ticket.getType());
		assertThat(result.getCount()).isEqualTo(ticket.getCount());
		assertThat(result.getPercentOf()).isEqualTo(ticket.getPercentOf());

	}
	@Test
	void addtickettostock() {
		// create test entries
		Location location2 = locationManagement.createLocation("TestTicketStock", 400, 15, Money.of(1500, EURO));
		Festival festival2 = festivalManagement.createFestival("TestTicketStock", location2, "2022-10-10", "2022-11-11",
				"Test Information");
		Ticket ticketStock =new Ticket("Abendkasse", Money.of(10, EURO), Ticket.TicketType.ABENDKASSE,
				festivalManagement.findAllByName("TestTicketStock").toList().get(0), 30);
		ticketRepository.save(ticketStock);
		ticketStockManagement.initializeNewTicketInInventory(ticketStock, ticketStock.getCount(), festival2);


		Streamable<TicketInventoryItem> tickets = ticketStockManagement.getTicketStockbyfestival(festival2.getName());
		assertThat(tickets.toList().get(0).equals(ticketStock));
		TicketInventoryItem result = tickets.toList().get(0);

		assertThat(result.getFestival().equals(ticketStock.getFestival()));
		assertThat(result.getProduct().getName().equals(ticketStock.getName()));
		assertThat(result.getProduct().getPrice().equals(ticketStock.getPrice()));








	}


}
