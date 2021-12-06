package festivalmanager.festival;

import java.util.List;

import org.salespointframework.core.DataInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import festivalmanager.location.LocationManagement;

@Order(20)
@Component
public class FestivalDataInitializer implements DataInitializer {

    private final Logger log = LoggerFactory.getLogger(FestivalDataInitializer.class);

    private final FestivalManagement festivalManagement;
    private final LocationManagement locationManagement;

    public FestivalDataInitializer(FestivalManagement festivalManagement, LocationManagement locationManagement) {
        Assert.notNull(festivalManagement, "festivalManagement must not be null!");
        Assert.notNull(locationManagement, "locationManagement must not be null!");
        this.festivalManagement = festivalManagement;
        this.locationManagement = locationManagement;
    }

    @Override
    public void initialize() {
        log.info("Initializing data for {}", getClass().getSimpleName());

        List.of(new Festival("Weihnachtsfestival", locationManagement.findAllByName("Arteum").toList().get(0),
                "2022-12-24", "2022-12-31", "Weihnachtsfestival Beschreibnung", true),
                new Festival("Maifeld Derby 2021", locationManagement.findAllByName("Mannheim").toList().get(0),
                        "2022-09-03", "2022-09-05",
                        "Maifeld Derby ist ein Indie, Rock, Pop und Electronic Festival, das vom 03.09.2021 bis 05.09.2021 in Mannheim (DE) stattgefunden hat."
                                + " Das Festival wurde von ca. 5.000 Fans besucht. Tickets kosteten EUR 80,00. Die Top-Acts waren Drangsal, Sophie Hunger und The Notwist."
                                + " Darüber hinaus waren Dives, Molchat Doma, Feng Suave, Culk, Cari Cari, Dÿse und viele mehr gebucht.",
                        true))
                .forEach(festivalManagement::createFestival);
    }
}
