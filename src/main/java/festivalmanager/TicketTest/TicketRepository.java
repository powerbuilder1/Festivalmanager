package festivalmanager.TicketTest;

import festivalmanager.lineup.LineUp;
import org.salespointframework.catalog.Catalog;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

public interface TicketRepository extends Catalog<Ticket> {


	static final Sort DEFAULT_SORT = Sort.by("productIdentifier").descending();

	Iterable<Ticket> findByType(Ticket.TicketType type, Sort sort);

	default Iterable<Ticket> findByType(Ticket.TicketType type) {
		return findByType(type, DEFAULT_SORT);
	}

}
