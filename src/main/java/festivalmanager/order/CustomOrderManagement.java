package festivalmanager.order;

import festivalmanager.catering.Food;
import festivalmanager.catering.FoodCatalog;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.stock.ReorderForm;
import org.salespointframework.order.Cart;
import org.salespointframework.order.CartItem;
import org.salespointframework.order.Order;
import org.salespointframework.order.OrderManagement;
import org.salespointframework.payment.Cash;
import org.salespointframework.quantity.Quantity;
import org.salespointframework.useraccount.UserAccount;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

	public String buy(Cart cart, Optional<UserAccount> userAccount) {
		return userAccount.map(account -> {
			Order order = new Order(account, Cash.CASH);
			cart.addItemsTo(order);
			orderOrderManagement.payOrder(order);
			try {
				orderOrderManagement.completeOrder(order);
				cart.clear();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return "redirect:/catering/sale";
		}).orElse("redirect:/catering/sale");
	}
}
