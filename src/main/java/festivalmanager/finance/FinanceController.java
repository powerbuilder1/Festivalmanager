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

    /**
     * Constructor
     * @param financeManagement for accessing the finances
     */
    public FinanceController(FinanceManagement financeManagement) {
        Assert.notNull(financeManagement, "financeManagement must not be null");
        this.financeManagement = financeManagement;
    }

    /**
     * Show the finances
     * @param model
     * @param id of the festival
     * @return string of template
     */
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

        Map<String, Data> catering = finance.entrySet().stream().filter(e -> e.getKey().startsWith("c"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        model.addAttribute("catering", catering);

        model.addAttribute("sum", financeManagement.getSum(id));
        
        System.out.println(financeManagement.getFinance(id));
        return "finance_overview";
    }


}
