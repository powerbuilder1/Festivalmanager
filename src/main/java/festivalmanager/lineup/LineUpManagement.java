package festivalmanager.lineup;

import festivalmanager.festival.Festival;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.location.Location;
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

	LineUpManagement(LineUpRepository LineUpRepository, FestivalManagement festivalManagement) {
		Assert.notNull(LineUpRepository, "festivalRepository must not be null");
		Assert.notNull(festivalManagement, "festivalmanagement must not be null");


		this.LineUpRepository = LineUpRepository;
		this.festivalManagement = festivalManagement;


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

	public void addBand (Long id, BandForm bandForm )
	{
		System.out.println("Welcome1");
		LineUpRepository.findById(id).ifPresent(lineUp -> {
			System.out.println("Welcome");
			lineUp.addBandto(
					new Band(
							bandForm.getName(),
							Money.of(bandForm.getPrice(), Currencies.EURO),
							bandForm.getStage(),
							bandForm.getPerformanceHour()
					)
			);
			LineUpRepository.save(lineUp);
		});

	}
	public void deleteBand( Long id, String bandname) {
		LineUpRepository.findById(id).ifPresent(lineUp -> {

			Iterator itr = lineUp.getBands().iterator();
		while( itr.hasNext())
		{
			Band delBand = (Band) itr.next() ;
			if (delBand.getName().equals(bandname))
			{
				itr.remove();
			}
			else
			{
				System.out.println("This band has not been found");
			}
		}

			LineUpRepository.save(lineUp);

		});
	}

	public LineUp createLineUp (Festival festival) {
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