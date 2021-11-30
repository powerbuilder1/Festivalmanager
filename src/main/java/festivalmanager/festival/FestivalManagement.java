package festivalmanager.festival;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import festivalmanager.location.Location;
import festivalmanager.location.LocationManagement;

@Service
@Transactional
public class FestivalManagement {

    private final FestivalRepository festivalRepository;
    private final LocationManagement locationManagement;

    FestivalManagement(FestivalRepository festivalRepository, LocationManagement locationManagement) {
        Assert.notNull(festivalRepository, "festivalRepository must not be null");
        Assert.notNull(locationManagement, "locationManagement must not be null");
        this.festivalRepository = festivalRepository;
        this.locationManagement = locationManagement;
    }

    public Festival createFestival(Festival festival) {
        Assert.notNull(festival, "festival must not be null");
        if (festival.getLocation() == null) {
            festival.setLocation(locationManagement.findById(festival.getLocationIdentifier()));
        }
        return festivalRepository.save(festival);
    }

    public Festival createFestival(String name, Location location, String beginDate, String endDate,
            String information) {
        Festival festival = new Festival(name, location, beginDate, endDate, information);
        return createFestival(festival);
    }

    public Streamable<Festival> findAllFestivals() {
        return festivalRepository.findAll();
    }

    public Festival findById(long id) {
        return festivalRepository.findById(id).orElse(null);
    }

    public Streamable<Festival> findAllByName(String name) {
        return festivalRepository.findAll().filter(festival -> festival.getName().equals(name));
    }

    public boolean deleteById(long id) {
        festivalRepository.deleteById(id);
        return true;
    }

}
