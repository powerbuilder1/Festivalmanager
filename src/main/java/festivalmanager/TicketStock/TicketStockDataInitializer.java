package festivalmanager.TicketStock;



import festivalmanager.Ticket.TicketRepository;
import festivalmanager.festival.FestivalManagement;
import org.salespointframework.core.DataInitializer;
import org.salespointframework.quantity.Quantity;
import org.springframework.stereotype.Component;

@Component
public class TicketStockDataInitializer implements DataInitializer {

	private final TicketStockInventory inventory;
	private final TicketRepository ticketCatalog;
	private final FestivalManagement festivalManagement;


	/**
	 * Constructor for the TicketStockDataInitializer.
	 * @param inventory
	 * @param ticketCatalog
	 * @param festivalManagement

	 */
	public TicketStockDataInitializer(TicketStockInventory inventory,
		TicketRepository ticketCatalog, FestivalManagement festivalManagement) {
		this.inventory = inventory;
		this.ticketCatalog = ticketCatalog;
		this.festivalManagement = festivalManagement;

	}
	/**
	 * Initializes the ticket items with the tickets found in the ticket catalog
	 */
	@Override
	public void initialize() {
		if(inventory.findAll().iterator().hasNext()) {
			return;
		}
		ticketCatalog.findAll().forEach(ticket -> {
			if (inventory.findByProduct(ticket).isEmpty() && !festivalManagement.findAllFestivals().isEmpty()) {
				inventory.save(new TicketInventoryItem(ticket, Quantity.of(ticket.getCount()), ticket.getFestival()));
			}
		});

	}
}
