package festivalmanager.lineup;

import festivalmanager.festival.Festival;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;


/**
 * Repository for storing all LineUps
 */
public interface LineUpRepository extends CrudRepository<LineUp, Long> {

	/**
	 * Returns all LineUps
	 * @return Streamable of all LineUps
	 */
	@Override
	Streamable<LineUp> findAll();




}
