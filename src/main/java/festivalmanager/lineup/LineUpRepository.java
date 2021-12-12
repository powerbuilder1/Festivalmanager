package festivalmanager.lineup;

import festivalmanager.festival.Festival;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;


public interface LineUpRepository extends CrudRepository<LineUp, Long> {

	@Override
	Streamable<LineUp> findAll();




}
