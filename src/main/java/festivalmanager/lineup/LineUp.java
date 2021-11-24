package festivalmanager.lineup;
import festivalmanager.festival.Festival;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class LineUp {

	private @Id
	@GeneratedValue
	long id;
	protected static Festival festival;
	public String Index;
	protected static ArrayList<Band> Lineup = new ArrayList<Band>();

	public LineUp(String Index, Festival festival) {
		this.Index = Index;
		this.festival = festival;
	}

	@SuppressWarnings({"unused", "deprecation"})
	public LineUp() {
	}

	public void addBandto(Band band) {
		Lineup.add(band);
	}

	public static Festival getFestival() {
		return festival;
	}

	public String getIndex() {
		return Index;
	}

	public ArrayList<String> getLineupnames() {
		ArrayList<String> names = new ArrayList<>();
		for (Band band : Lineup) {
			names.add(band.getName());
		}

		return names;
	}

	public ArrayList<String> getLineupUhrzeiten() {
		ArrayList<String> Uhrzeit = new ArrayList<>();
		for (Band band : Lineup) {
			Uhrzeit.add(band.getPerformanceHour());
		}

		return Uhrzeit;
	}
}