package festivalmanager.finance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;


/**
 * @author Conrad
 * stores Finance objects in a database
 */
public interface FinanceRepository extends CrudRepository<Finance, Long> {

    @Override
    Streamable<Finance> findAll();

}
