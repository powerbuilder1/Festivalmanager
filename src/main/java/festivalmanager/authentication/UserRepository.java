package festivalmanager.authentication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

public interface UserRepository extends CrudRepository<User, Long> {
	@Override
	Streamable<User> findAll();
}