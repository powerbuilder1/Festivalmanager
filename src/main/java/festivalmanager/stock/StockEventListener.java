package festivalmanager.stock;

import festivalmanager.authentication.User;
import festivalmanager.communication.Room;
import festivalmanager.festival.FestivalManagement;
import org.salespointframework.inventory.InventoryEvents;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StockEventListener {

	private FestivalManagement festivalManagement;

	public StockEventListener() {
		this.festivalManagement = null;
	}

	public void setFestivalManagement(FestivalManagement festivalManagement) {
		this.festivalManagement = festivalManagement;
	}

	@EventListener
	public void handleEvent(InventoryEvents.StockShort event) {

		User system = festivalManagement.getUserManagement().findByName("SYSTEM");
		if (system == null) {
			System.out.println("initMessages: system not found");
			return;
		}

		Room room = festivalManagement.getCommunicationManagement().findRoomByName("public");
		if (room == null) {
			System.out.println("initMessages: room 'public' not found");
			return;
		}

		festivalManagement.getCateringManagement().findProductById(event.getProductId()).ifPresent(food -> {
			festivalManagement.getCommunicationManagement().sendMessage(system, "[STOCK] WARNING: Only " +
					event.getCurrentQuantity() + " of " + food.getName() + " are in Stock.", room);
		});
	}

}
