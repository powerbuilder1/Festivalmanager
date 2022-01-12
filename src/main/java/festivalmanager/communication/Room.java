package festivalmanager.communication;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Conrad
 * Room class
 * used to connect users to a room via Participants
 */
@Entity
public class Room {

    /**
     * id of the room (primary key) {@ling Long}
     */
    private @Id @GeneratedValue Long id;

    /**
     * name of the room {@link String}
     */
    private String name;

    /**
     * participants of the room {@link Set<Participants>}
     */
    @OneToMany(mappedBy = "room")
    Set<Participants> participants;

    /**
     * default constructor
     */
    public Room() {
        // default constructor
    }

    /**
     * constructor
     * @param name the name {@link String}
     */
    public Room(String name) {
        setName(name);
    }

    /**
     * getter
     * @return the id {@link Long}
     */
    public String getName() {
        return name;
    }

    /**
     * setter
     * @param name the name {@link String}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter
     * @return the id {@link Long}
     */
    public Long getId() {
        return this.id;
    }

    /**
     * setter
     * @param id the id {@link Long}
     */
    public void setId(Long id) {
        this.id = id;
    }

}