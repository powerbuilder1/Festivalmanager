package festivalmanager.communication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

/**
 * @author Conrad
 * RoomRepository is used for storing the rooms of the communication package
 */
public interface RoomRepository extends CrudRepository<Room, Long> {

    @Override
    Streamable<Room> findAll();

}
