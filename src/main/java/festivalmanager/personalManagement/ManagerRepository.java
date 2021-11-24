package festivalmanager.personalManagement;

import festivalmanager.authentication.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

public interface ManagerRepository extends CrudRepository<Manager, Long> {
	@Override
	Streamable<Manager> findAll();
}