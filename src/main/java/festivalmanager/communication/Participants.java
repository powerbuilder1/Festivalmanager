package festivalmanager.communication;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import festivalmanager.authentication.User;

/**
 * @author Conrad
 * A class to represent the access a user has to a room
 */
@Entity
public class Participants {

    private @Id @GeneratedValue Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    Room room;

    String access;

    /**
     * default constructor
     */
    public Participants() {
        // default constructor
    }

    /**
     * constructor
     * @param user the user {@link User}
     * @param room the room {@link Room}
     * @param access the access {@link String}
     */
    public Participants(User user, Room room, String access) {
        setUser(user);
        setRoom(room);
        setAccess(access);
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

    /**
     * getter
     * @return the user {@link User}
     */
    public User getUser() {
        return this.user;
    }

    /**
     * setter
     * @param user the user {@link User}
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * getter
     * @return the room {@link Room}
     */
    public Room getRoom() {
        return this.room;
    }

    /**
     * setter
     * @param room the room {@link Room}
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * getter
     * @return the access {@link String}
     */
    public String getAccess() {
        return this.access;
    }

    /**
     * setter
     * @param access the access {@link String}
     */
    public void setAccess(String access) {
        this.access = access;
    }

}
