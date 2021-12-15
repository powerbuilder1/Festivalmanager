package festivalmanager.lineup;

import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.location.Location;
import festivalmanager.location.LocationForm;
import org.javamoney.moneta.Money;
import org.salespointframework.core.Currencies;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.money.CurrencyUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class LineUpManagement {
	private static LineUpRepository LineUpRepository;
	private static FestivalManagement festivalManagement;

	LineUpManagement(LineUpRepository LineUpRepository) {
		Assert.notNull(LineUpRepository, "festivalRepository must not be null");

		this.LineUpRepository = LineUpRepository;

	}

	public void setFestivalManagement(FestivalManagement festivalManagement) {
		this.festivalManagement = festivalManagement;
	}
	/**
	 * creates a new Lineup and it adds it to the repository
	 *
	 * @param lineUp
	 * @return
	 */
	public static LineUp createLineUp(LineUp lineUp) throws Exception {
		Assert.notNull(lineUp, "lineUp must not be null");
		if (lineUp.getFestival() == null) {
			lineUp.setFestival(festivalManagement.findById(lineUp.getFestivalIdentifier()));
			lineUp.setId((festivalManagement.findById(lineUp.getFestivalIdentifier())).getId());}
		for (LineUp lineups: LineUpRepository.findAll() )
		{
			if (lineups.getId() == lineUp.getId())
			{
				throw new Exception(" THIS LINEUP ALREADY EXISTS");
			}
		}
		return LineUpRepository.save(lineUp);
	}
	/**
	 * add new band to an specific LineUp
	 *
	 * @param id
	 * @param bandForm
	 * @return
	 */
	public void addBand(Long id, BandForm bandForm) {
		System.out.println("Welcome1");
		LineUpRepository.findById(id).ifPresent(lineUp -> {
			System.out.println("Welcome");
			try {
				lineUp.addBandto(
						new Band(
								bandForm.getName(),
								Money.of(bandForm.getPrice(), Currencies.EURO),
								bandForm.getStage(),
								bandForm.getPerformanceHour()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			LineUpRepository.save(lineUp); });

	}
	/**
	 * deletes a specific band from an specific LineUp
	 *
	 * @param id
	 * @param bandname
	 * @return
	 */
	public void deleteBand(Long id, String bandname) {
		System.out.println(bandname);
		LineUpRepository.findById(id).ifPresent(lineUp -> {
			lineUp.getBands().removeIf(filterBand -> filterBand.getName1().equals(bandname));
			LineUpRepository.save(lineUp); });
	}
	/**
	 * edit an specific band in an specific LineUp
	 *
	 * @param id
	 * @param Bandname
	 * @param form
	 * @return
	 */
	public void updateBand(long id, String Bandname, BandForm form) {

		LineUpRepository.findById(id).ifPresent(lineUp -> {

			Iterator itr = lineUp.getBands().iterator();
			while (itr.hasNext()) {
				Band editBand = (Band) itr.next();
				if (editBand.getName1().equals(Bandname)) {
					editBand.setName1(form.getName());
					editBand.setStage(form.getStage());
					editBand.setPrice(Money.of(form.getPrice(), Currencies.EURO));
					editBand.setPerformanceHour(form.getPerformanceHour());
					break;
				} else {
					System.out.println("This band has not been found");
				}
			}

			LineUpRepository.save(lineUp);

		});
	}

	public LineUp createLineUp(Festival festival) throws Exception {
		LineUp lineUp = new LineUp(festival);
		return createLineUp(lineUp);
	}
	/**
	 * Finds all the Lineups in a repository
	 *
	 * @return
	 */
	public Streamable<LineUp> findAllLineUp() {
		return LineUpRepository.findAll();
	}
	/**
	 * Finds a lineUp with the given Id
	 * @param id
	 * @return
	 */
	public LineUp findById(long id) {
		return LineUpRepository.findById(id).orElse(null);
	}


}