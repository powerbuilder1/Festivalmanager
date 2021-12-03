package festivalmanager.order;

import festivalmanager.catering.Food;
import festivalmanager.catering.FoodCatalog;
import festivalmanager.stock.ReorderForm;
import org.salespointframework.order.Cart;
import org.salespointframework.order.Order;
import org.salespointframework.order.OrderManagement;
import org.salespointframework.quantity.Quantity;
import org.springframework.stereotype.Service;

@Service
public class CustomOrderManagement {

	private final OrderManagement<Order> orderOrderManagement;
	private final FoodCatalog foodCatalog;

	public CustomOrderManagement(OrderManagement<Order> orderOrderManagement, FoodCatalog foodCatalog) {
		this.orderOrderManagement = orderOrderManagement;
		this.foodCatalog = foodCatalog;
	}

	public void addFoodToCard(Cart cart, ReorderForm orderForm) {
		// find food product by ID
		foodCatalog.findById(orderForm.getFoodItemId()).ifPresent(food -> {
			// add food items to card
			cart.addOrUpdateItem(food, Quantity.of(orderForm.getAmount()));
		});
	}
}
