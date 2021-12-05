package festivalmanager.communication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

public interface ParticipantsRepository extends CrudRepository<Participants, Long> {

    @Override
    Streamable<Participants> findAll();

}
