package festivalmanager.communication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

public interface RoomRepository extends CrudRepository<Room, Long> {

    @Override
    Streamable<Room> findAll();

}
