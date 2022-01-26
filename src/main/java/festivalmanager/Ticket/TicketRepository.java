package festivalmanager.Ticket;


import festivalmanager.catering.Food;
import festivalmanager.festival.Festival;
import org.salespointframework.catalog.Catalog;
import org.springframework.data.domain.Sort;

import org.springframework.data.util.Streamable;
/**
 * Repository for storing tickets
 */
public interface TicketRepository extends Catalog<Ticket> {

	/**
	 * Returns tickets of an specific festival
	 * @return Streamable of Tickets
	 */
	Streamable<Ticket> findTicketByFestival(Festival festival);
	/**
	 * Returns all Ticket
	 * @return Streamable of all Tickets
	 */
	Streamable<Ticket> findAll();

}
