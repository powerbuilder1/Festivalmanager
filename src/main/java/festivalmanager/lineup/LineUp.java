package festivalmanager.lineup;
import festivalmanager.festival.Festival;
import org.javamoney.moneta.Money;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
public class LineUp {

	@OneToOne
	private Festival festival;
	private long festivalIdentifier;
	private long festivalIdIdentifier;


	private @Id
	long id;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Band> SetofBands = new ArrayList<Band>();

	public LineUp() {

	}

	/**
	 * LineUp constructor
	 *
	 * @param festival
	 */
	public LineUp(Festival festival) {

		this.festival = festival;
		this.setId(festival.getId());
	}

	public long getFestivalIdentifier() {
		return festivalIdentifier;
	}

	public void setFestivalIdentifier(long festivalIdentifier) {
		this.festivalIdentifier = festivalIdentifier;
	}

	public void addBandto(Band band) throws Exception {
		for( Band bands : SetofBands  ){
			if (bands.getName1().equals(band.getName1()) | bands.getStage().equals(band.getStage())   ) {
				throw new Exception( "It cannot be added to the lineup, sorry try with different inputs");
			}
		}
		if ( SetofBands.size() > this.festival.getLocation().getMaxStages() )
		{
			throw new Exception(" You can not add more bands to this festival");
		}
		else {
			SetofBands.add(band);
		}
	}

	public Festival getFestival() {
		return festival;
	}


	public long getFestivalIdIdentifier() {
		return festivalIdIdentifier;
	}

	public void setFestivalIdIdentifier(long festivalIdIdentifier) {
		this.festivalIdIdentifier = festivalIdIdentifier;
	}

	public ArrayList<String> getBandnames() {
		ArrayList<String> names = new ArrayList<>();
		for (Band band : SetofBands) {
			names.add(band.getName1());
		}

		return names;
	}
	public ArrayList<Double> getPriceOfBands() {
		ArrayList<Double> prices = new ArrayList<>();
		for (Band band : SetofBands) {
			prices.add(band.getPrice().getNumber().doubleValueExact());
		}

		return prices;
	}
	public ArrayList<String> getLineupUhrzeiten() {
		ArrayList<String> Uhrzeit = new ArrayList<>();
		for (Band band: SetofBands) {
				Uhrzeit.add(band.getPerformanceHour());
			}
				return Uhrzeit ;
	}

	public ArrayList<String> getStagesinLineUp()
	{
		ArrayList<String> setOfStages = new ArrayList<>();
		int sum = 1;
		for ( int i =0; i<festival.getLocation().getMaxStages(); i++)
		{
			setOfStages.add("Buehne "+ sum);
			sum = sum+1;
		}
		return setOfStages;
	}

	public ArrayList<String> getHoursofLineUp()
	{
		ArrayList<String> getHours = new ArrayList<>();
		int Hour1 = 1;
		int Hour2 = 3;
		for ( int i = 0; i<22; i++)
		{
			getHours.add(Hour1+":00 - "+Hour2+":00");
			Hour1 = Hour1+1;
			Hour2 = Hour2+1;
		}
		return getHours;
	}

	public long getId() {
		return id;
	}

	public String Festivalname(){
		return festival.getName()+ " ---- Line UP";
	}

	public List<Band> getBands() {
		return SetofBands;
	}

	public ArrayList<String> getStages() {
		ArrayList<String> stages = new ArrayList<>();
		for (Band band: SetofBands) {
			stages.add(band.getStage());
		}
		return stages ;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
	}


	public void setLineup(List<Band> SetofBands) {
		this.SetofBands = SetofBands;
	}

}

