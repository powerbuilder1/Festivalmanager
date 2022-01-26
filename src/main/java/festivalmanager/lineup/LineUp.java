package festivalmanager.lineup;
import festivalmanager.festival.Festival;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LineUp {

	@OneToOne (optional=true)
	private Festival festival;
	private long festivalIdentifier;
	private long festivalIdIdentifier;


	private @Id
	long id;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Band> setOfBands = new ArrayList<Band>();

	/**
	 * default constructor
	 */
	public LineUp() {
	}

	/**
	 * LineUp constructor
	 *
	 * @param festival the festival in which the lineup is assigned to {@link Festival}
	 */
	public LineUp(Festival festival) {

		this.festival = festival;
		this.setId(festival.getId());
	}
	/**
	 * getter for festival identifier
	 * @return
	 */

	public long getFestivalIdentifier() {
		return festivalIdentifier;
	}

	/**
	 * setter
	 *
	 * @param festivalIdentifier  to set the festival identifier {@link Long}
	 */
	public void setFestivalIdentifier(long festivalIdentifier) {
		this.festivalIdentifier = festivalIdentifier;
	}
	/**
	 * add a band to an specific LineUp
	 *
	 * @param band
	 * @return
	 */
	public void addBandto(Band band) throws IllegalStateException {
		for( Band bands : setOfBands) {
			if (bands.getName1().equals(band.getName1()) | bands.getStage().equals(band.getStage())) {
				throw new IllegalStateException( "It cannot be added to the lineup, sorry try with different inputs");
			}
		}
		if ( setOfBands.size() > this.festival.getLocation().getMaxStages()) {
			throw new IllegalStateException(" You can not add more bands to this festival");
		} else {
			setOfBands.add(band);
		}
	}
	/**
	 * getter for festival of LineUp
	 * @return
	 */
	public Festival getFestival() {
		return festival;
	}

	/**
	 * getter for festival id identifier
	 * @return
	 */
	public long getFestivalIdIdentifier() {
		return festivalIdIdentifier;
	}

	public void setFestivalIdIdentifier(long festivalIdIdentifier) {
		this.festivalIdIdentifier = festivalIdIdentifier;
	}

	/**
	 * getter for the names of the bands in the LineUp
	 * @return
	 */
	public ArrayList<String> getBandnames() {
		ArrayList<String> names = new ArrayList<>();
		for (Band band : setOfBands) {
			names.add(band.getName1());
		}

		return names;
	}
	/**
	 * getter for the prices of the bands in the LineUp
	 * @return
	 */
	public ArrayList<Double> getPriceOfBands() {
		ArrayList<Double> prices = new ArrayList<>();
		for (Band band : setOfBands) {
			prices.add(band.getPrice().getNumber().doubleValueExact());
		}

		return prices;
	}
	/**
	 * getter for the performance hours of the bands in the LineUp
	 * @return
	 */
	public ArrayList<String> getLineupUhrzeiten() {
		ArrayList<String> Uhrzeit = new ArrayList<>();
		for (Band band: setOfBands) {
			Uhrzeit.add(band.getPerformanceHour());
		}
		return Uhrzeit ;
	}
	/**
	 * getter for the stages of the bands in the LineUp
	 * @return
	 */
	public ArrayList<String> getStagesinLineUp() {
		ArrayList<String> setOfStages = new ArrayList<>();
		int sum = 1;
		for ( int i =0; i<festival.getLocation().getMaxStages(); i++) {
			setOfStages.add("Buehne "+ sum);
			sum = sum+1;
		}
		return setOfStages;
	}
	/**
	 * getter for the specific hours that can be found in the LineUp
	 * @return
	 */
	public ArrayList<String> getHoursofLineUp() {
		ArrayList<String> getHours = new ArrayList<>();
		int Hour1 = 1;
		int Hour2 = 3;
		for ( int i = 0; i<22; i++) {
			getHours.add(Hour1+":00 - "+Hour2+":00");
			Hour1 = Hour1+1;
			Hour2 = Hour2+1;
		}
		return getHours;
	}
	/**
	 * getter for the id of the LineUp
	 * @return
	 */
	public long getId() {
		return id;
	}
	/**
	 * string of the name of the festival assigned to an specific LineUp
	 * @return
	 */
	public String Festivalname() {
		return festival.getName()+ " ---- Line UP";
	}


	/**
	 * string of the name of the festival assigned to an specific LineUp
	 * @return
	 */
	public String getFestivalName() { return festival.getName(); };
	/**
	 * getter for the bands in the LineUp
	 * @return
	 */
	public List<Band> getBands() {
		return setOfBands;
	}
	/**
	 * getter for the stages of the bands in the LineUp
	 * @return
	 */
	public ArrayList<String> getStages() {
		ArrayList<String> stages = new ArrayList<>();
		for (Band band: setOfBands) {
			stages.add(band.getStage());
		}
		return stages ;
	}
	/**
	 * setter
	 *
	 * @param id  to set the id {@link Long}
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * setter
	 *
	 * @param festival  to set the festival {@link Festival}
	 */
	public void setFestival(Festival festival) {
		this.festival = festival;
	}

	/**
	 * setter
	 *
	 * @param setOfBands  to set the the list of bands {@link List}
	 */
	public void setLineup(List<Band> setOfBands) {
		this.setOfBands = setOfBands;
	}

}

