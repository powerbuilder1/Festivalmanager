package festivalmanager.lineup;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

public interface BandRepository extends CrudRepository<Band , Long> {

	@Override
	Streamable<Band> findAll();

}

