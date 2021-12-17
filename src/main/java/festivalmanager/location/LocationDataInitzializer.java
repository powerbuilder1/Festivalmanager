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
                new Location("Mannheim", 3000, 6, Money.of(300000, EURO))).forEach(locationManagement::createLocation);

        // set example
        Location location = locationManagement.findAllByName("Hockenheimring").toList().get(0);
        location.setData(
                "{\"map\":{\"center\":{\"lat\":51.03775474108291,\"lng\":13.763154457672128},\"zoom\":15},\"polygons\":[{\"color\":\"#63FFAD\",\"name\":\"Stage 1\",\"paths\":[{\"lat\":51.03485212802968,\"lng\":13.759199205203542},{\"lat\":51.037876175757376,\"lng\":13.762473718448177},{\"lat\":51.04011751419271,\"lng\":13.756881776614675},{\"lat\":51.03739043514997,\"lng\":13.754508485599043}]},{\"color\":\"#FE216E\",\"name\":\"Camping\",\"paths\":[{\"lat\":51.03684921068535,\"lng\":13.769820752902517},{\"lat\":51.039373843907946,\"lng\":13.77240862063934},{\"lat\":51.041480121130746,\"lng\":13.766709390445241},{\"lat\":51.03918495149373,\"lng\":13.764271726413252}]}]}");
        location.setStaticImage(
                "https://maps.googleapis.com/maps/api/staticmap?center=51.03775474108291,13.763154457672128&zoom=15&size=640x400&maptype=satellite&path=color:0x1E63FFAD|weight:1|fillcolor:0x1E63FFAD|51.03485212802968,13.759199205203542|51.037876175757376,13.762473718448177|51.04011751419271,13.756881776614675|51.03739043514997,13.754508485599043&path=color:0x1EFE216E|weight:1|fillcolor:0x1EFE216E|51.03684921068535,13.769820752902517|51.039373843907946,13.77240862063934|51.041480121130746,13.766709390445241|51.03918495149373,13.764271726413252&markers=anchor:center|icon:https://i.imgbun.com/awQkl2cYybU.png|51.03748482111119,13.75849110202361&markers=anchor:center|icon:https://i.imgbun.com/HFFNG9VltAGs.png|51.03916466590805,13.768340173526296&key=AIzaSyAuAHl9sypEnysXjsS7SbNJ5e7x44kAFmY");
        locationManagement.updateLocation(location);
    }
}
