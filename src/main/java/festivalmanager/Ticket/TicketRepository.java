package festivalmanager.Ticket;


import festivalmanager.catering.Food;
import festivalmanager.festival.Festival;
import org.salespointframework.catalog.Catalog;
import org.springframework.data.domain.Sort;

import org.springframework.data.util.Streamable;

public interface TicketRepository extends Catalog<Ticket> {

	Streamable<Ticket> findTicketByFestival(Festival festival);
	Streamable<Ticket> findAll();

}
