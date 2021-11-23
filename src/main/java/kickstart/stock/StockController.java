package kickstart.stock;

import org.salespointframework.inventory.UniqueInventoryItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockController {

	final private StockManagment stockManagment;

	public StockController(StockManagment stockManagment) {
		this.stockManagment = stockManagment;
	}

	@GetMapping(path = "stock")
	public String getCurrentStock(Model model) {
/*
		for (UniqueInventoryItem item : stockManagment.getCurrentStock()) {
			System.out.println(item.getProduct().getName());
		}
*/
		model.addAttribute("stock", stockManagment.getCurrentStock());
		return "stock";
	}
}
