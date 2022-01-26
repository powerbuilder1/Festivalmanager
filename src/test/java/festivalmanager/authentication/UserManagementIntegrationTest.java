package festivalmanager.authentication;

import festivalmanager.AbstractIntegrationTests;
import festivalmanager.catering.CateringManagement;
import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.location.Location;
import festivalmanager.location.LocationManagement;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.salespointframework.useraccount.UserAccountManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Streamable;
import org.springframework.test.annotation.DirtiesContext;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.salespointframework.core.Currencies.EURO;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class UserManagementIntegrationTest extends AbstractIntegrationTests {

	@Autowired
	private FestivalManagement festivalManagement;

	@Autowired
	private CateringManagement cateringManagement;

	@Autowired
	private UserManagement userManagement;

	@Autowired
	private LocationManagement locationManagement;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAccountManagement userAccountManagement;

	/**
	 * Make sure the test festivals(s) don't exist before the test
	 * @param name
	 */

	void deleteLocation(String name) {
		Streamable<Location> locations = locationManagement.findAllByName(name);
		for(Location location : locations)
		{
			locationManagement.deleteById(location.getId());
		}
	}

	/**
	 * Make sure the test location(s) don't exist before the test
	 * @param name
	 */

	void deleteFestival(String name) {
		Streamable<Festival> festivals = festivalManagement.findAllByName(name);
		for(Festival festival : festivals)
		{
			festivalManagement.deleteById(festival.getId());
		}
	}



	@Test
	void createCateringStaff() {

		deleteFestival("Test");
		deleteLocation("Test");
		userManagement.deleteUsersByName("TestCateringUser1");
		// create test entries
		Location location = locationManagement.createLocation("Test", 200, 10, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("Test", location, "2022-10-10", "2022-11-11",
				"Test Information");
		User testUser = userManagement.createCateringStaff(
				new UserForm(
						"TestCateringUser1",
						"123",
						"TestAddress",
						"Catering",
						"TestWorkplace",
						festival.getId()
				)
		);

		User user = userManagement.findByName(testUser.getName());
		assertThat(userManagement.findAllByName(user.getName())).hasSize(1);
		deleteFestival("Test");
		deleteLocation("Test");
	}

	@Test
	void createPlanningStaff() {

		deleteFestival("Test");
		deleteLocation("Test");
		userManagement.deleteUsersByName("TestPlanningUser");
		// create test entries
		Location location = locationManagement.createLocation("Test", 200, 10, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("Test", location, "2022-10-10", "2022-11-11",
				"Test Information");
		User testUser = userManagement.createPlanningStaff(
				new UserForm(
						"TestPlanningUser",
						"123",
						"TestAddress",
						"Planning",
						"TestWorkplace",
						festival.getId()
				)
		);

		User user = userManagement.findByName(testUser.getName());
		assertThat(userManagement.findAllByName(user.getName())).hasSize(1);
		deleteFestival("Test");
		deleteLocation("Test");
	}

	@Test
	void createSystem() {

		deleteFestival("Test");
		deleteLocation("Test");
		userManagement.deleteUsersByName("TestSystem");
		// create test entries
		Location location = locationManagement.createLocation("Test", 200, 10, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("Test", location, "2022-10-10", "2022-11-11",
				"Test Information");
		User testUser = userManagement.createSystem(
				new UserForm(
						"TestSystem",
						"123",
						"TestAddress",
						"System",
						"TestWorkplace",
						festival.getId()
				)
		);

		User user = userManagement.findByName(testUser.getName());
		assertThat(userManagement.findAllByName(user.getName())).hasSize(1);
		deleteFestival("Test");
		deleteLocation("Test");
	}

	@Test
	void createFestivalDirector() {

		deleteFestival("Test");
		deleteLocation("Test");
		userManagement.deleteUsersByName("TestDirector");
		// create test entries
		Location location = locationManagement.createLocation("Test", 200, 10, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("Test", location, "2022-10-10", "2022-11-11",
				"Test Information");
		User testUser = userManagement.createFestivalDirector(
				new UserForm(
						"TestDirector",
						"123",
						"TestAddress",
						"Festivalleiter",
						"TestWorkplace",
						festival.getId()
				)
		);

		User user = userManagement.findByName(testUser.getName());
		assertThat(userManagement.findAllByName(user.getName())).hasSize(1);
		deleteFestival("Test");
		deleteLocation("Test");
	}

	@Test
	void createSecurityStaff() {

		deleteFestival("Test");
		deleteLocation("Test");
		userManagement.deleteUsersByName("TestSecurity");
		// create test entries
		Location location = locationManagement.createLocation("Test", 200, 10, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("Test", location, "2022-10-10", "2022-11-11",
				"Test Information");
		User testUser = userManagement.createSecurityStaff(
				new UserForm(
						"TestSecurity",
						"123",
						"TestAddress",
						"Security",
						"TestWorkplace",
						festival.getId()
				)
		);

		User user = userManagement.findByName(testUser.getName());
		assertThat(userManagement.findAllByName(user.getName())).hasSize(1);
		deleteFestival("Test");
		deleteLocation("Test");
	}

	@Test
	void findAll() {
		Streamable<User> users = userManagement.findAll();
		assertThat(users).hasSizeGreaterThan(-1);
	}



	@Test
	void deleteUsersByName() {
		deleteFestival("Test");
		deleteLocation("Test");
		userManagement.deleteUsersByName("TestSecurity1");
		// create test entries
		Location location = locationManagement.createLocation("Test", 200, 10, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("Test", location, "2022-10-10", "2022-11-11",
				"Test Information");
		User testUser = userManagement.createSecurityStaff(
				new UserForm(
						"TestSecurity1",
						"123",
						"TestAddress",
						"Security",
						"TestWorkplace",
						festival.getId()
				)
		);

		userManagement.deleteUsersByName(testUser.getName());

		assertThat(userAccountManagement.findByUsername(testUser.getName()).isEmpty());
		assertThat(userRepository.findById(testUser.getId()).isEmpty());
		deleteFestival("Test");
		deleteLocation("Test");
		userManagement.deleteUsersByName("TestSecurity1");
	}
	@Test
	void deleteUsersByFestivalId() {
		deleteFestival("Test");
		deleteLocation("Test");
		userManagement.deleteUsersByName("TestSecurity3");
		// create test entries
		Location location = locationManagement.createLocation("Test", 200, 10, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("Test", location, "2022-10-10", "2022-11-11",
				"Test Information");
		UserForm userForm = 	new UserForm(
				"TestSecurity3",
				"123",
				"TestAddress",
				"Security",
				"TestWorkplace",
				festival.getId()
		);
		User testUser = userManagement.createSecurityStaff(userForm);

		userManagement.deleteUsersByFestivalId(userForm.getFestivalId());
		assertThat(userAccountManagement.findByUsername(testUser.getName()).isEmpty());
		assertThat(userRepository.findById(testUser.getId()).isEmpty());
		deleteFestival("Test");
		deleteLocation("Test");
		userManagement.deleteUsersByName("TestSecurity3");
	}

}
