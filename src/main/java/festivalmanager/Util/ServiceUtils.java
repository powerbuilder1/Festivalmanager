package festivalmanager.Util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import festivalmanager.festival.FestivalManagement;
import festivalmanager.location.LocationManagement;

/**
 * @author Conrad
 * Singleton class for accessing managements inside the validator
 */
@Component
public class ServiceUtils {

    private static ServiceUtils instance;

    @Autowired
    private LocationManagement locationManagement;
    @Autowired
    private FestivalManagement festivalManagement;

    /**
     * Singleton "constructor"
     */
    @PostConstruct
    public void fillInstance() {
        instance = this;
    }

    /**
     * Returns the LocationManagement
     * @return {@link LocationManagement}
     */
    public static LocationManagement getLocationManagement() {
        return instance.locationManagement;
    }

    /**
     * Returns the FestivalManagement
     * @return {@link FestivalManagement}
     */
    public static FestivalManagement getFestivalManagement() {
        return instance.festivalManagement;
    }

}
