package festivalmanager.communication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {

    @Override
    Streamable<ChatMessage> findAll();

}
