package festivalmanager.communication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

/**
 * @author Conrad
 * ChatMessageRepository class
 * used to store a message in the database
 */
public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {

    @Override
    Streamable<ChatMessage> findAll();

}
