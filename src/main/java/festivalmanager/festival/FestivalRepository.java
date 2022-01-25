package festivalmanager.festival;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

/**
 * Repository for storing Festivals
 */
public interface FestivalRepository extends CrudRepository<Festival, Long> {

    /**
     * Returns all Festivals
     * @return Streamable of all Festivals
     */
    @Override
    Streamable<Festival> findAll();

}
