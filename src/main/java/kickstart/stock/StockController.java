package kickstart.stock;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockController {

	final private StockManagment stockManagment;

	public StockController(StockManagment stockManagment) {
		this.stockManagment = stockManagment;
	}

	@GetMapping(path = "stock")
	public void getCurrentStock() {
	}
}
