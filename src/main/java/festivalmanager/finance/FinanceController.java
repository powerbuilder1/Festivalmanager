package festivalmanager.finance;

import java.util.Map;
import java.util.stream.Collectors;

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
        Map<String, Data> finance = financeManagement.getFinance(id);
        
        Map<String, Data> bands = finance.entrySet().stream()
                .filter(e -> e.getKey().startsWith("b"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        model.addAttribute("bands", bands);

        Map<String, Data> locations = finance.entrySet().stream()
                .filter(e -> e.getKey().startsWith("l"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        model.addAttribute("locations", locations);

        Map<String, Data> rest = finance.entrySet().stream()
                .filter(e -> e.getKey().startsWith("x"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        model.addAttribute("rest", rest);


        model.addAttribute("sum", financeManagement.getSum(id));
        
        System.out.println(financeManagement.getFinance(id));
        return "finance_overview";
    }


}
