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
import org.springframework.data.util.Streamable;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.salespointframework.core.Currencies.EURO;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
		// add new test Food item to Catalog
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

		NewFoodItemForm testFoodItemFormEdit = new NewFoodItemForm(
				"TestFoodEdit",
				10.6
		);

		cateringManagement.addItemToCatalog(testFoodItemForm, Optional.ofNullable(testUser.getUserAccount()));

		Streamable<Food> testFoodCatalog = cateringManagement.getCatalog(Optional.ofNullable(testUser.getUserAccount()));
		assertThat(testFoodCatalog).hasSize(1);

		Food result = testFoodCatalog.toList().get(0);
		cateringManagement.editItemFromCatalog(result, testFoodItemFormEdit);

		testFoodCatalog = cateringManagement.getCatalog(Optional.ofNullable(testUser.getUserAccount()));
		assertThat(testFoodCatalog).hasSize(1);

		result = testFoodCatalog.toList().get(0);

		// check
		assertThat(testFoodItemFormEdit.getName().equals(result.getName()));
		assertThat(Money.of(testFoodItemFormEdit.getPrice(), EURO).equals(result.getPrice()));

	}

	@Test
	void checkDeleteItemFromCatalog() {
		// create test data
		// add new test Food item to Catalog
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

		Streamable<Food> testFoodCatalog = cateringManagement.getCatalog(Optional.ofNullable(testUser.getUserAccount()));
		assertThat(testFoodCatalog).hasSize(1);

		Food firstResult = testFoodCatalog.toList().get(0);

		cateringManagement.deleteItemFromCatalog(firstResult);
		testFoodCatalog = cateringManagement.getCatalog(Optional.ofNullable(testUser.getUserAccount()));

		// check
		assertThat(testFoodCatalog).hasSize(0);

	}

}

