package festivalmanager.Ticket;

import java.util.List;
import org.javamoney.moneta.Money;
import static org.salespointframework.core.Currencies.*;
import org.salespointframework.core.DataInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;



@Component
public class TicketDataInitializer implements DataInitializer {


	private final Logger LOG = LoggerFactory.getLogger(festivalmanager.Ticket.TicketDataInitializer.class);

	@Override
	public void initialize() {

		LOG.info("Creating default  entries.");


		List.of(new TagesKarte(Money.of(9.99, EURO)," 12334fr191", 24),
				new TagesKarte(Money.of(9.99, EURO)," 12334fr191", 24),
				new TagesKarte(Money.of(9.99, EURO)," 1233thgh191", 24),
				new TagesKarte(Money.of(9.99, EURO)," 12334fr191", 24),
				new TagesKarte(Money.of(9.99, EURO)," 1233434th", 24));

	}
}

