package festivalmanager.catering;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserForm;
import festivalmanager.authentication.UserManagement;
import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.location.Location;
import festivalmanager.location.LocationManagement;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.salespointframework.core.Currencies.EURO;

@SpringBootTest
@AutoConfigureMockMvc
public class CateringManagementIntegrationTests {

	@Autowired
	private FestivalManagement festivalManagement;

	@Autowired
	private CateringManagement cateringManagement;

	@Autowired
	private UserManagement userManagement;

	@Autowired
	private LocationManagement locationManagement;

	@Test
	void checkAddItemFromCatalog() {
		// create test data
		Location location = locationManagement.createLocation("Test", 200, 10, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("Test", location, "2022-10-10", "2022-11-11", "Test Information");

		User testUser = userManagement.createCateringStaff(
				new UserForm(
						"TestCateringUser",
						"123",
						"TestAddress",
						"Catering",
						"TestWorkplace",
						festival.getId()
				)
		);

		NewFoodItemForm testFoodItemForm = new NewFoodItemForm(
				"TestFood",
				20.5
		);

		cateringManagement.addItemToCatalog(testFoodItemForm, Optional.ofNullable(testUser.getUserAccount()));

		Food testFood = new Food(testFoodItemForm.getName(), Money.of(testFoodItemForm.getPrice(), EURO), festival);
		List<Food> testFoodList = new ArrayList<>();
		testFoodList.add(testFood);

		// check
		assertThat(cateringManagement.getCatalog(festival.getId()).toList().equals(testFoodList));
	}

	@Test
	void checkEditItemFromCatalog() {
		// create test data
		Location location = locationManagement.createLocation("Test", 200, 10, Money.of(500, EURO));
		Festival festival = festivalManagement.createFestival("Test", location, "2022-10-10", "2022-11-11", "Test Information");

		User testUser = userManagement.createCateringStaff(
				new UserForm(
						"TestCateringUser",
						"123",
						"TestAddress",
						"Catering",
						"TestWorkplace",
						festival.getId()
				)
		);

		NewFoodItemForm testFoodItemForm = new NewFoodItemForm(
				"TestFood",
				20.5
		);

		cateringManagement.addItemToCatalog(testFoodItemForm, Optional.ofNullable(testUser.getUserAccount()));

		Food testFood = new Food(testFoodItemForm.getName(), Money.of(testFoodItemForm.getPrice(), EURO), festival);
		List<Food> testFoodList = new ArrayList<>();
		testFoodList.add(testFood);

		// check
		assertThat(cateringManagement.getCatalog(festival.getId()).toList().equals(testFoodList));
	}
}

