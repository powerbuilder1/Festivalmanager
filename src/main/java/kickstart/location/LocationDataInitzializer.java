package kickstart.location;

import org.salespointframework.core.DataInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Order(10)
@Component
public class LocationDataInitzializer implements DataInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(LocationDataInitzializer.class);

    private final LocationManagement locationManagement;

    LocationDataInitzializer(LocationManagement locationManagement) {
        Assert.notNull(locationManagement, "locationManagement must not be null");
        this.locationManagement = locationManagement;
    }

    @Override
    public void initialize() {
    }
}
