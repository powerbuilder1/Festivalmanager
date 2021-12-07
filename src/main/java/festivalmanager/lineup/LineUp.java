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
	private List<Band> Lineup = new ArrayList<Band>();

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

	public void addBandto(Band band) {
		Lineup.add(band);
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
		for (Band band : Lineup) {
			names.add(band.getName1());
		}

		return names;
	}
	public ArrayList<String> getLineupUhrzeiten() {
		ArrayList<String> Uhrzeit = new ArrayList<>();
		for (Band band: Lineup)
			{
				Uhrzeit.add(band.getPerformanceHour());
			}
				return Uhrzeit ;
	}

	public long getId() {
		return id;
	}

	public String Festivalname(){
		return festival.getName()+ " ---- Line UP";
	}

	public List<Band> getBands() {
		return Lineup;
	}

	public ArrayList<String> getStages() {
		ArrayList<String> stages = new ArrayList<>();
		for (Band band: Lineup)
		{
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


	public void setLineup(List<Band> lineup) {
		this.Lineup = lineup;
	}

}

