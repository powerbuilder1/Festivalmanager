package festivalmanager.finance;

import festivalmanager.festival.Festival;
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
	FinanceManagement financeManagement;
	FinanceRepository financeRepository;
	FestivalManagement festivalManagement;
	FestivalRepository festivalRepository;
	LocationManagement locationManagement;

	public FinanceController(FinanceManagement financeManagement,FinanceRepository financeRepository, FestivalManagement festivalManagement, FestivalRepository festivalRepository, LocationManagement locationManagement){
		this.financeManagement = financeManagement;
		this.financeRepository = financeRepository;
		this.festivalManagement = festivalManagement;
		this.festivalRepository = festivalRepository;
		this.locationManagement = locationManagement;
	}
	@PreAuthorize("hasAnyRole('BOSS', 'FESTIVALDIRECTOR')")
	@GetMapping("/finance")
	String finance(/*@PathVariable long id,*/ Model model) {
		model.addAttribute("finance",financeManagement.findAllFinances());
		model.addAttribute("title","Finances");
		return "finance";
	}

	@PreAuthorize("hasAnyRole('BOSS', 'FESTIVALDIRECTOR')")
	@GetMapping("/finance/{id}/overview")
	String overview(@PathVariable long id, Model model){
		Festival festival = festivalManagement.findById(id);
		model.addAttribute("finance",financeManagement.findAllFinances());
		model.addAttribute("title","Finances");
		return "finance_overview";
	}

	@PreAuthorize("hasRole('BOSS')")
	@GetMapping("finance/pay")
	String pay(/*@PathVariable long id,*/ Model model){
		return "finance_pay";
	}

}
