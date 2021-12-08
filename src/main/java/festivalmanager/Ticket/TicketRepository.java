package festivalmanager.Ticket;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;




public interface TicketRepository extends CrudRepository<Ticket, Long> {
	@Override
	Streamable<Ticket> findAll();


}
