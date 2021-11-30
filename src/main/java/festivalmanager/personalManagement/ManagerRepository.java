package festivalmanager.personalManagement;

import festivalmanager.authentication.User;
import org.hibernate.usertype.UserType;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

public interface ManagerRepository extends CrudRepository<Manager, Long> {

	@Override
	Streamable<Manager> findAll();


}