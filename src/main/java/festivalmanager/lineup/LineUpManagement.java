package festivalmanager.lineup;

import festivalmanager.festival.Festival;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


@Service
@Transactional
public class LineUpManagement {
	private static LineUpRepository LineUpRepository;

	LineUpManagement(LineUpRepository LineUpRepository) {
		Assert.notNull(LineUpRepository, "festivalRepository must not be null");
		this.LineUpRepository = LineUpRepository;
	}
	public static LineUp createLineUp(LineUp lineUp) {
		Assert.notNull(lineUp, "lineUp must not be null");
		return LineUpRepository.save(lineUp);
	}
	public LineUp createLineUp (String Index, Festival festival) {
		LineUp lineUp = new LineUp(Index, festival);
		return createLineUp(lineUp);
	}
	public Streamable<LineUp> findAllLineUp() {
		return LineUpRepository.findAll();
	}
}