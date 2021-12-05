package festivalmanager.communication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import festivalmanager.authentication.User;

/**
 * ChatMessage class
 * used to store a message in the database
 * 
 */
@Entity
public class ChatMessage {

    private @Id @GeneratedValue Long id;

    @Column(columnDefinition = "TEXT")
    private String message;

    @ManyToOne
    private User sender;

    @ManyToOne
    private Room room;

    public ChatMessage() {
        // default constructor
    }

    public ChatMessage(String message, User sender, Room room) {
        this.message = message;
        this.sender = sender;
        this.room = room;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return this.sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
