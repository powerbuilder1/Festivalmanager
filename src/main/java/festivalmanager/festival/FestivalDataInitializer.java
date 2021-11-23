package festivalmanager.festival;

import org.salespointframework.core.DataInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(20)
@Component
public class FestivalDataInitializer implements DataInitializer {

    private final Logger LOG = LoggerFactory.getLogger(FestivalDataInitializer.class);

    @Override
    public void initialize() {
        LOG.info("Initializing data for {}", getClass().getSimpleName());
    }
}
