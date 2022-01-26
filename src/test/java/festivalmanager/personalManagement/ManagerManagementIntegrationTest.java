package festivalmanager.personalManagement;


import festivalmanager.AbstractIntegrationTests;
import festivalmanager.authentication.User;
import festivalmanager.authentication.UserForm;
import festivalmanager.authentication.UserManagement;
import festivalmanager.authentication.UserRepository;
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
public class ManagerManagementIntegrationTest{

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

	@Autowired
	private ManagerManagement managerManagement;

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
	void checkDeleteUser() {
		deleteFestival("Test");
		deleteLocation("Test");
		userManagement.deleteUsersByName("TestSecurity4");
		// create test entries
		Location location = locationManagement.createLocation("Test", 200, 10, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("Test", location, "2022-10-10", "2022-11-11",
				"Test Information");
		User testUser = userManagement.createSecurityStaff(
				new UserForm(
						"TestSecurity4",
						"123",
						"TestAddress",
						"Security",
						"TestWorkplace",
						festival.getId()
				)
		);

		managerManagement.deleteUser(testUser);

		assertThat(userAccountManagement.findByUsername(testUser.getName()).isEmpty());
		assertThat(userRepository.findById(testUser.getId()).isEmpty());
		deleteFestival("Test");
		deleteLocation("Test");
		userManagement.deleteUsersByName("TestSecurity4");
	}


}
