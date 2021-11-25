package festivalmanager.stock;

import org.salespointframework.inventory.UniqueInventoryItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StockController {

	final private StockManagment stockManagment;

	public StockController(StockManagment stockManagment) {
		this.stockManagment = stockManagment;
	}

	@GetMapping(path = "stock")
	public String getCurrentStock(Model model) {

		for (UniqueInventoryItem item : stockManagment.getCurrentStock()) {
			System.out.println(item.getProduct().getName());
		}

		model.addAttribute("stock", stockManagment.getCurrentStock());
		return "stock";
	}

	@PostMapping(path = "stock/deleteAllInventoryItems/{inventoryItem}")
	public String deleteAllInventoryItems(@PathVariable UniqueInventoryItem inventoryItem) {
		stockManagment.deleteAllInventoryItems(inventoryItem);
		return "stock";
	}
	 //TEST
	@PostMapping(path = "stock/test/deleteAll")
	public String deleteAll() {
		stockManagment.deleteAll();
		return "stock";
	}

}
