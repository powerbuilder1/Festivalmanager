package festivalmanager.communication;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import festivalmanager.authentication.User;

/**
 * @author Conrad
 * ChatMessage class
 * used to store a message in the database
 */
@Entity
public class ChatMessage {

    /**
     * id of the message {@link Long}
     */
    private @Id @GeneratedValue Long id;

    /**
     * the message {@link String}
     */
    @Column(columnDefinition = "TEXT")
    private String message;

    /**
     * the sender of the message {@link User}
     */
    @ManyToOne
    private User sender;

    /**
     * the room the message is in {@link Room}
     */
    @ManyToOne
    private Room room;

    /**
     * the date the message was sent {@link Date}
     */
    private Date timeStamp;

    /**
     * default constructor
     */
    public ChatMessage() {
        // default constructor
    }

    /**
     * constructor
     * @param message the message {@link String}
     * @param sender the sender {@link User}
     * @param room the room {@link Room}
     * the date the message was sent is auto-generated
     */
    public ChatMessage(String message, User sender, Room room) {
        setMessage(message);
        setSender(sender);
        setRoom(room);
        setTimeStamp(new Date());
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
     * @return the message {@link String}
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * setter
     * @param message the message {@link String}
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * getter
     * @return the sender {@link User}
     */
    public User getSender() {
        return this.sender;
    }

    /**
     * setter
     * @param sender the sender {@link User}
     */
    public void setSender(User sender) {
        this.sender = sender;
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
     * @return the date the message was sent {@link Date}
     */
    public void setTimeStamp(Date date) {
        this.timeStamp = date;
    }

    /**
     * getter
     * @return the date the message was sent {@link Date}
     */
    public Date getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * toString method
     * @return {@link String}
     * representation of the ChatMessage
     */
    public String toString() {
        return "{" +
                " id=" + this.id +
                ", message='" + this.message + '\'' +
                ", sender=" + this.sender.getName() +
                ", room=" + this.room.getName() +
                "}";
    }

}
