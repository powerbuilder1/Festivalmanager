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
}