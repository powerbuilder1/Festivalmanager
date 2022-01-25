package festivalmanager.authentication;

import org.salespointframework.useraccount.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import java.util.Optional;

/**
 * A repository interface to manage {@link User} instances.
 */

public interface UserRepository extends CrudRepository<User, Long> {

	/**
	 * Re-declared {@link CrudRepository#findAll()} to return a {@link Streamable} instead of {@link Iterable}.
	 */
	@Override
	Streamable<User> findAll();

	/**
	 * find user by UserAccount
	 * @param userAccount
	 * @return
	 */
	User findUserByUserAccount(UserAccount userAccount);

	/**
	 * delete users by festivalId
	 * @param festivalId
	 */
	void deleteUsersByFestival_Id(Long festivalId);

	/**
	 * delete user by name
	 * @param name
	 */
	void deleteUsersByName(String name);

	/**
	 * find user by festivalId
	 * @param festivalId
	 * @return
	 */
	Optional<User> findUsersByFestival_Id(Long festivalId);

}