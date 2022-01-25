package festivalmanager.authentication;

import org.salespointframework.useraccount.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

/**
 * A repository interface to manage {@link User} instances.
 */

public interface UserRepository extends CrudRepository<User, Long> {

	/**
	 * Re-declared {@link CrudRepository#findAll()} to return a {@link Streamable} instead of {@link Iterable}.
	 */
	@Override
	Streamable<User> findAll();

	User findUserByUserAccount(UserAccount userAccount);

}