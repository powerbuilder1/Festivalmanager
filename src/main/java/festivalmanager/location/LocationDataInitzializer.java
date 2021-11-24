package festivalmanager.location;

import static org.salespointframework.core.Currencies.*;

import java.util.List;

import org.javamoney.moneta.Money;
import org.salespointframework.core.DataInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Order(10)
@Component
public class LocationDataInitzializer implements DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(LocationDataInitzializer.class);

    private final LocationManagement locationManagement;

    LocationDataInitzializer(LocationManagement locationManagement) {
        Assert.notNull(locationManagement, "locationManagement must not be null");
        this.locationManagement = locationManagement;
    }

    @Override
    public void initialize() {
        // already initialized
        if (locationManagement.findAllLocations().iterator().hasNext()) {
            return;
        }
        log.info("Creating default location entries");
        List.of(new Location("Hockenheimring", 5000, 3, Money.of(500000, EURO)),
                new Location("Kraftwerk Mitte", 4000, 2, Money.of(2300000, EURO)),
                new Location("Arteum", 1000, 5, Money.of(10000, EURO)),
                new Location("Kreuzberg", 2000, 4, Money.of(20000, EURO)),
                new Location("Mannheim", 3000, 5, Money.of(300000, EURO))).forEach(locationManagement::createLocation);
    }
}
