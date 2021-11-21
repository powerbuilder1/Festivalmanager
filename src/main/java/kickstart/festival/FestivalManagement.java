package kickstart.festival;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import kickstart.location.Location;

@Service
@Transactional
public class FestivalManagement {

    private final FestivalRepository festivalRepository;

    FestivalManagement(FestivalRepository festivalRepository) {
        Assert.notNull(festivalRepository, "festivalRepository must not be null");
        this.festivalRepository = festivalRepository;
    }

    public Festival createFestival(Festival festival) {
        Assert.notNull(festival, "festival must not be null");
        return festivalRepository.save(festival);
    }

    public Festival findFestival(String name, Location location, String beginDate, String endDate, String information) {
        Festival festival = new Festival(name, location, beginDate, endDate, information);
        return createFestival(festival);
    }

}
