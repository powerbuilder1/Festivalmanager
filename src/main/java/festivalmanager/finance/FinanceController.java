package festivalmanager.finance;

import festivalmanager.festival.FestivalRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.location.LocationManagement;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FinanceController {
	FinanceRepository financeRepository;
	FestivalManagement festivalManagement;
	FestivalRepository festivalRepository;
	LocationManagement locationManagement;

	public FinanceController(FinanceRepository financeRepository, FestivalManagement festivalManagement, FestivalRepository festivalRepository, LocationManagement locationManagement){
		this.financeRepository = financeRepository;
		this.festivalManagement = festivalManagement;
		this.festivalRepository = festivalRepository;
		this.locationManagement = locationManagement;
	}
	@PreAuthorize("hasAnyRole('BOSS', 'FESTIVALDIRECTOR')")
	@GetMapping("/finance")
	String finance(/*@PathVariable long id,*/ Model model) {
		return "finance";
	}

	@PreAuthorize("hasAnyRole('BOSS', 'FESTIVALDIRECTOR')")
	@GetMapping("/finance/overview")
	String overview(/*@PathVariable long id,*/ Model model){
		return "finance_overview";
	}

	@PreAuthorize("hasRole('BOSS')")
	@GetMapping("finance/pay")
	String pay(/*@PathVariable long id,*/ Model model){
		return "finance_pay";
	}

}
