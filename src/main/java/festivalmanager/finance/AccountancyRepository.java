package festivalmanager.finance;

import org.salespointframework.accountancy.AccountancyEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

/**
 * Repository for {@link AccountancyEntry}s.
 */
public interface AccountancyRepository extends CrudRepository<AccountancyEntry, Long> {

    /**
     * Returns all {@link AccountancyEntry}s.
     */
    @Override
    Streamable<AccountancyEntry> findAll();

}
