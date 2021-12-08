package festivalmanager.authentication;

import org.hibernate.usertype.UserType;
import org.salespointframework.useraccount.UserAccount;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

public interface UserRepository extends CrudRepository<User, Long> {

	@Override
	Streamable<User> findAll();

	User findUserByUserAccount(UserAccount userAccount);

}