package festivalmanager.communication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

/**
 * @author Conrad
 * ParticipantsRepository is used for storing the participants of the communication package
 */
public interface ParticipantsRepository extends CrudRepository<Participants, Long> {

    @Override
    Streamable<Participants> findAll();

}
