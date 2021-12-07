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

	public static LineUp createLineUp(LineUp lineUp) {
		Assert.notNull(lineUp, "lineUp must not be null");
		if (lineUp.getFestival() == null) {
			lineUp.setFestival(festivalManagement.findById(lineUp.getFestivalIdentifier()));
			lineUp.setId((festivalManagement.findById(lineUp.getFestivalIdentifier())).getId());

		}
		return LineUpRepository.save(lineUp);
	}

	public void addBand(Long id, BandForm bandForm) {
		System.out.println("Welcome1");
		LineUpRepository.findById(id).ifPresent(lineUp -> {
			System.out.println("Welcome");
			lineUp.addBandto(
					new Band(
							bandForm.getName(),
							Money.of(bandForm.getPrice(), Currencies.EURO),
							bandForm.getStage(),
							bandForm.getPerformanceHour()));
			LineUpRepository.save(lineUp);
		});

	}

	public void deleteBand(Long id, String bandname) {
		LineUpRepository.findById(id).ifPresent(lineUp -> {

		Iterator itr = lineUp.getBands().iterator();
		while( itr.hasNext())
		{
			Band delBand = (Band) itr.next() ;
			if (delBand.getName1().equals(bandname))
			{
				itr.remove();
			}
			else
			{
				System.out.println("This band has not been found");
			}

			LineUpRepository.save(lineUp);

		};
	}
	/*
	 * public void updateBand(BandForm form) {
	 * Assert.notNull(form, "form must not be null");
	 * }
	 */

	public void updateBand(long id, String Bandname, BandForm form) {

		LineUpRepository.findById(id).ifPresent(lineUp -> {

			Iterator itr = lineUp.getBands().iterator();
			while (itr.hasNext()) {
				Band editBand = (Band) itr.next();
				if (editBand.getName1().equals(Bandname)) {
					editBand.setName1(form.getName());
					editBand.setStage(form.getStage());
					editBand.setPerformanceHour(form.getPerformanceHour());
				} else {
					System.out.println("This band has not been found");
				}
			}

			LineUpRepository.save(lineUp);

		});
		/*
		 * LineUpRepository.findById(id).ifPresent(lineUp -> {
		 * for (Band bands: lineUp.getBands())
		 * {
		 * bands.setName1(form.getName());
		 * bands.setPrice(Money.of(form.getPrice(), Currencies.EURO));
		 * bands.setPerformanceHour(form.getPerformanceHour());
		 * bands.setStage(form.getStage());
		 * }
		 * });
		 */
	}

	public LineUp createLineUp(Festival festival) {
		LineUp lineUp = new LineUp(festival);
		return createLineUp(lineUp);
	}

	public Streamable<LineUp> findAllLineUp() {
		return LineUpRepository.findAll();
	}

	public LineUp findById(long id) {
		return LineUpRepository.findById(id).orElse(null);
	}

}