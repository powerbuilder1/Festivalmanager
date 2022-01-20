package festivalmanager.finance;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Conrad
 */
@Controller
public class FinanceController {
    
    private final FinanceManagement financeManagement;

    public FinanceController(FinanceManagement financeManagement) {
        Assert.notNull(financeManagement, "financeManagement must not be null");
        this.financeManagement = financeManagement;
    }

    @PreAuthorize("hasRole('BOSS')")
    @GetMapping("/festival/{id}/finance")
    public String getFinance(Model model, @PathVariable Long id) {
        model.addAttribute("data", financeManagement.getFinance(id));
        model.addAttribute("sum", financeManagement.getSum(id));
        

        System.out.println(financeManagement.getFinance(id));
        return "finance_overview";
    }


}
