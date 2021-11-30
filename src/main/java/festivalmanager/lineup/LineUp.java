package festivalmanager.lineup;
import festivalmanager.festival.Festival;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LineUp {

	private @Id long id;
	public String festival;
	public String Index;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Band> Lineup = new ArrayList<Band>();

	public LineUp(String Index, String festival, long id) {
		this.Index = Index;
		this.festival = festival;
		this.id = id ;
	}

	@SuppressWarnings({"unused", "deprecation"})
	public LineUp() {

	}

	public void addBandto(Band band) {
		Lineup.add(band);
	}

	public String getFestival() {
		return festival;
	}

	public String getIndex() {
		return Index;
	}

	public ArrayList<String> getBandnames() {
		ArrayList<String> names = new ArrayList<>();
		for (Band band : Lineup) {
			names.add(band.getName());
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
		return festival+ " ---- Line UP";
	}

	public Iterable<Band> getBands() {
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

	}
