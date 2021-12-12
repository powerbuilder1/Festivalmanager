package festivalmanager.finance;


import org.salespointframework.core.DataInitializer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.stereotype.Component;

@Component
public class FinanceDataInitializer implements DataInitializer{
	private final Logger log = LoggerFactory.getLogger(FinanceDataInitializer.class);

	private final FinanceRepository financeRepository;

	FinanceDataInitializer(FinanceRepository financeRepository){
		Assert.notNull(financeRepository,"financeRepository must not be Null!");
		this.financeRepository = financeRepository;
	}

	@Override
	public void initialize(){
		
	}
}
