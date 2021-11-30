package festivalmanager.festival;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import festivalmanager.location.Location;

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

}
