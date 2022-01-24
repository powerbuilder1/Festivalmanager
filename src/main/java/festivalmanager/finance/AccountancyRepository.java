package festivalmanager.finance;

import org.salespointframework.accountancy.AccountancyEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

public interface AccountancyRepository extends CrudRepository<AccountancyEntry, Long> {

    @Override
    Streamable<AccountancyEntry> findAll();

}
