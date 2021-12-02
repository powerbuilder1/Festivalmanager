package festivalmanager.lineup;

import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.location.Location;
import org.javamoney.moneta.Money;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class LineUpManagement {
	private static LineUpRepository LineUpRepository;
	private static BandRepository bandRepository;
	private static FestivalManagement festivalManagement;

	LineUpManagement(LineUpRepository LineUpRepository, FestivalManagement festivalManagement, BandRepository bandRepository) {
		Assert.notNull(LineUpRepository, "festivalRepository must not be null");
		Assert.notNull(festivalManagement, "festivalmanagement must not be null");
		Assert.notNull(bandRepository, "bandrepository must not be null");


		this.LineUpRepository = LineUpRepository;
		this.festivalManagement = festivalManagement;
		this.bandRepository = bandRepository;

	}
	public static LineUp createLineUp (LineUp lineUp) {
		Assert.notNull(lineUp, "lineUp must not be null");
		if (lineUp.getFestival() == null)
		{
			lineUp.setFestival(festivalManagement.findById(lineUp.getFestivalIdentifier()));
			lineUp.setId ((festivalManagement.findById(lineUp.getFestivalIdentifier())).getId());

		}
		return LineUpRepository.save(lineUp);
	}
	public LineUp createLineUp (Festival festival) {
		LineUp lineUp = new LineUp(festival);
		return createLineUp(lineUp);
	}

	public static Band createBand (Band band) {
		Assert.notNull(band, "band must not be null");
		return bandRepository.save(band);
	}

	public Band createBand (String name, Money price, String stage, String performanceHour) {
		Band band = new Band(name, price, stage, performanceHour);
		return createBand(band);

	}

	public Streamable<LineUp> findAllLineUp() {
		return LineUpRepository.findAll();
	}

	public List<Band> findAllBands() {
		List<LineUp> lineups = (LineUpRepository.findAll()).toList();
		List<Band> bands = new ArrayList<>();
		for (int i = 0 ; i< lineups.size(); i++)
		{
			List<Band> bandsOfLineUp = new ArrayList<>();
			bandsOfLineUp = (lineups.get(i)).getBands();
			for (int j = 0; j< bandsOfLineUp.size();j++)
			{
				bands.add(bandsOfLineUp.get(j));
			}

		}
		return bands;

	}

	public LineUp findById(long id) {
		return LineUpRepository.findById(id).orElse(null);
	}

}