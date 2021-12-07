package festivalmanager.Util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import festivalmanager.festival.FestivalManagement;
import festivalmanager.location.LocationManagement;

@Component
public class ServiceUtils {

    private static ServiceUtils instance;

    @Autowired
    private LocationManagement locationManagement;
    @Autowired
    private FestivalManagement festivalManagement;

    @PostConstruct
    public void fillInstance() {
        instance = this;
    }

    public static LocationManagement getLocationManagement() {
        return instance.locationManagement;
    }

    public static FestivalManagement getFestivalManagement() {
        return instance.festivalManagement;
    }

}
