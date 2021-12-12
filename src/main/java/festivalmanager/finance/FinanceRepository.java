package festivalmanager.finance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

public interface FinanceRepository extends CrudRepository<Finance, Long> {

	@Override
	Streamable<Finance> findAll();
}
