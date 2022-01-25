package festivalmanager.finance;

import java.util.Map;

import javax.money.MonetaryAmount;
import javax.transaction.Transactional;

import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import festivalmanager.festival.FestivalManagement;
import festivalmanager.lineup.Band;
import festivalmanager.lineup.LineUp;
import festivalmanager.location.Location;


@Service
@Transactional
public class FinanceManagement {

    private FestivalManagement festivalManagement;
    private FinanceRepository financeRepository;
    private AccountancyRepository accountancyRepository;

    /**
     * Constructor
     * @param financeRepository for accessing the finances
     * @param accountancyRepository for accessing the accountancy history (orders)
     */
    public FinanceManagement(FinanceRepository financeRepository, AccountancyRepository accountancyRepository) {
        Assert.notNull(financeRepository, "financeRepository must not be null");
        Assert.notNull(accountancyRepository, "accountancyRepository must not be null");
        this.financeRepository = financeRepository;
        this.accountancyRepository = accountancyRepository;
    }

    /**
     * sets the FestivalManagement
     * @param festivalManagement for accessing the festivals
     */
    public void setFestivalManagement(FestivalManagement festivalManagement) {
		this.festivalManagement = festivalManagement;
	}
    
    /**
     * Returns the finances of the festival
     * @param id
     * @return Map<{@link String}, {@link Data}>
     */
    public Map<String, Data> getFinance(long id) {
        
        if(festivalManagement.findById(id) == null) {
            return null;
        }

        Finance finance = financeRepository.findById(id).orElse(null);
        if(finance == null)
        {
            finance = financeRepository.save(new Finance(id));
        }

        // get location rent
        // update each time
        Location location = festivalManagement.findById(id).getLocation();
        long cents = moneyToLong(location.getRent());
        finance.overwriteData("l" + location.getName(), 1, -cents);

        // update line up
        LineUp lineUp = festivalManagement.getLineUpManagement().findById(id);
        for(Band band : lineUp.getBands())
        {
            String name = band.getName1();
            long price = moneyToLong(band.getPrice());
            finance.overwriteData("b" + name, 1, -price);
        }

        // tickets and catering
        for (var entry : accountancyRepository.findAll().toList())
        {
            finance.addData("xCatering + Tickets", 1, moneyToLong(entry.getValue()));
        }


        return finance.getFinanceData();
    }

    /**
     * Returns the sum of the finances for a festival
     * @param id of the festival
     * @return sum of costs and income
     */
    public long getSum(long id)
    {
        if(festivalManagement.findById(id) == null) {
            return 0;
        }

        Map<String, Data> financeData = getFinance(id);
        long sum = 0;

        for(var entry : financeData.entrySet())
        {
            sum += entry.getValue().price * entry.getValue().amount;
        }

        return sum;
    }

    /**
     * returns the long representation of Money
     * @param money
     * @return
     */
    public long moneyToLong(Money money)
    {
        String tmp = money.toString();
        tmp = tmp.replace(".", "");
        tmp = tmp.replace("EUR", "");
        tmp = tmp.replace(" ", "");
        long cents = Long.parseLong(tmp);
        return cents;
    }
    /**
     * returns the long representation of MonetaryAmount
     * @param money
     * @return
     */
    public long moneyToLong(MonetaryAmount money)
    {
        String tmp = money.toString();
        tmp = tmp.replace(".", "");
        tmp = tmp.replace("EUR", "");
        tmp = tmp.replace(" ", "");
        long cents = Long.parseLong(tmp);
        return cents;
    }


    public Finance getFinanceById(long id){
        return financeRepository.findAll().filter(finance -> finance.getId() == id).toList().get(0);
    }

}
