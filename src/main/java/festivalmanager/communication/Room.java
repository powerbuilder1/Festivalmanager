package festivalmanager.communication;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Room class
 * used to connect users to a room via Participants
 */
@Entity
public class Room {

    private @Id @GeneratedValue Long id;

    private String name;

    @OneToMany(mappedBy = "room")
    Set<Participants> participants;

    public Room() {
        // default constructor
    }

    public Room(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
