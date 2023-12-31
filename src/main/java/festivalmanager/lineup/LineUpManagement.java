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

import java.util.Iterator;

@Service
@Transactional
public class LineUpManagement {
	private static LineUpRepository LineUpRepository;
	private static FestivalManagement festivalManagement;
	public Band wantedBand;

	/**
	 * Constructor LineUpManagement
	 *
	 * @param LineUpRepository

	 */
	LineUpManagement(LineUpRepository LineUpRepository) {
		Assert.notNull(LineUpRepository, "festivalRepository must not be null");

		this.LineUpRepository = LineUpRepository;
	}
	/**
	 * setter
	 *
	 * @param festivalManagement the festivalmanagement to set {@link FestivalManagement}
	 */
	public void setFestivalManagement(FestivalManagement festivalManagement) {
		this.festivalManagement = festivalManagement;
	}
	/**
	 * creates a new Lineup and it adds it to the repository
	 *
	 * @param lineUp
	 * @return
	 */
	public static LineUp createLineUp(LineUp lineUp) throws IllegalStateException {
		Assert.notNull(lineUp, "lineUp must not be null");
		if (lineUp.getFestival() == null) {
			lineUp.setFestival(festivalManagement.findById(lineUp.getFestivalIdentifier()));
			lineUp.setId((festivalManagement.findById(lineUp.getFestivalIdentifier())).getId());
		}
		for (LineUp lineups: LineUpRepository.findAll() ) {
			if (lineups.getId() == lineUp.getId()) {
				throw new IllegalStateException(" THIS LINEUP ALREADY EXISTS");
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
			for (Band bands: lineUp.getBands()) {
				if ( bandForm.getStage().equals(bands.getStage())) {
					try {
						throw new IllegalStateException("This Stage is already occupied by " + bands.getName1());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			try {
				if (bandForm.getPrice()>0 && bandForm.getName().isEmpty() == false) {
					lineUp.addBandto(
							new Band(
									bandForm.getName(),
									Money.of(bandForm.getPrice(), Currencies.EURO),
									bandForm.getStage(),
									bandForm.getPerformanceHour()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			LineUpRepository.save(lineUp); 
		});

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
			LineUpRepository.save(lineUp);
		});
	}
	/**
	 * edit an specific band in an specific LineUp
	 *
	 * @param id
	 * @param form
	 * @return
	 */
	public void updateBand(long id, BandForm form) {
		LineUpRepository.findById(id).ifPresent(lineUp -> {
			Iterator itr = lineUp.getBands().iterator();
			while (itr.hasNext()) {
				Band editBand = (Band) itr.next();
				if (editBand.getName1().equals(form.getName())) {
						editBand.setName1(form.getName());
						editBand.setStage(form.getStage());
						if (form.getPrice()>0) {
							editBand.setPrice(Money.of(form.getPrice(), Currencies.EURO));
						}
						editBand.setPerformanceHour(form.getPerformanceHour());
						break;

				} else {
					System.out.println("This band has not been found");
				}
			}

			LineUpRepository.save(lineUp);
		});
	}
	/**
	 * create a new LineUp for a specific festival
	 * @param festival
	 * @return
	 */
	public LineUp createLineUp(Festival festival) throws IllegalStateException {
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
	/**
	 * Finds a band in a LineUp with the given name
	 * @param id
	 * @return
	 */
	public Band findBandByName ( long id, String name ) {
		LineUpRepository.findById(id).ifPresent(lineUp -> {
			for (Band bands : lineUp.getBands()) {
				if ( bands.getName1().equals(name)) {
					wantedBand = bands;

				}
			}
		});
		return wantedBand;
	}
	/**
	 * find lineup by festival
	 * @param name
	 * @return
	 */
	public Streamable<LineUp> findLineUpByFestivalName ( String name ) {
		return LineUpRepository.findAll().filter(lineup -> lineup.getFestivalName().equals(name));
	}
	/**
	 * delete a band of a LineUp with the given id
	 * @param id
	 * @return
	 */
	public void deleteById(long id) {
		if(LineUpRepository.findById(id).isPresent()) {
			LineUpRepository.deleteById(id);
		}

	}

}