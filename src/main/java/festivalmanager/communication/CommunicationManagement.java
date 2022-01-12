package festivalmanager.communication;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserManagement;

/**
 * @author Conrad
 * CommunicationManagement is a class that handels and processes information from the controller and the database
 * and prepares them for return
 */
@Service
@Transactional
public class CommunicationManagement {

    private final ChatMessageRepository chatMessageRepository;
    private final RoomRepository roomRepository;
    private final UserManagement userManagement;
    private final ParticipantsRepository participantsRepository;

    /**
     * constructor
     * @param chatMessageRepository {@link ChatMessageRepository}
     * @param roomRepository {@link RoomRepository}
     * @param userManagement {@link UserManagement}
     * @param participantsRepository {@link ParticipantsRepository}
     */
    public CommunicationManagement(ChatMessageRepository chatMessageRepository, RoomRepository roomRepository,
            UserManagement userManagement, ParticipantsRepository participantsRepository) {
        Assert.notNull(chatMessageRepository, "chatMessageRepository must not be null");
        Assert.notNull(roomRepository, "roomRepository must not be null");
        Assert.notNull(userManagement, "userManagement must not be null");
        Assert.notNull(participantsRepository, "participantsRepository must not be null");
        this.chatMessageRepository = chatMessageRepository;
        this.roomRepository = roomRepository;
        this.userManagement = userManagement;
        this.participantsRepository = participantsRepository;
    }

    /**
     * Creates a new room 
     * @param name the name
     * @return the created room {@link Room}
     */
    public Room createRoom(String name) {
        return roomRepository.save(new Room(name));
    }

    /**
     * Creates a new chat message
     * @param sender the sender {@link User}
     * @param message the message {@link String}
     * @param room the room {@link Room}
     * @return the created chat message {@link ChatMessage}
     */
    public ChatMessage sendMessage(User sender, String message, Room room) {
        Assert.notNull(sender, "sender must not be null");
        Assert.notNull(message, "message must not be null");
        Assert.notNull(room, "room must not be null");
        ChatMessage chatMessage = new ChatMessage(message, sender, room);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    /**
     * Creates a new chat message
     * @param userId the id of the sender {@link Long}
     * @param message the message {@link String}
     * @param roomId the id of the room {@link Long}
     * @return the created chat message {@link ChatMessage}
     */
    public ChatMessage sendMessage(long userId, String message, long roomId) {
        User sender = userManagement.findById(userId);
        Room room = roomRepository.findById(roomId).orElse(null);

        if (sender == null || room == null) {
            System.out.println("Sender or room not found");
            return null;
        }

        return sendMessage(sender, message, room);
    }

    /**
     * adds a user to a room
     * @param user the user {@link User}
     * @param room the room {@link Room}
     * @param access the access eq r/w/rw {@link Access}
     * @return
     */
    public boolean joinRoom(User user, Room room, String access) {
        Assert.notNull(user, "user must not be null");
        Assert.notNull(room, "room must not be null");
        Assert.notNull(access, "access must not be null");
        Assert.isTrue(access.length() > 0, "access must not be empty");
        participantsRepository.save(new Participants(user, room, access));
        return true;
    }

        /**
     * adds a user to a room
     * @param userId the id of the user {@link Long}
     * @param roomId the id of the room {@link Long}
     * @param access the access eq r/w/rw {@link Access}
     * @return
     */
    public boolean joinRoom(long userId, long roomId, String access) {
        User user = userManagement.findById(userId);
        Room room = roomRepository.findById(roomId).orElse(null);

        if (user == null || room == null) {
            System.out.println("User or room not found");
            return false;
        }
        return joinRoom(user, room, access);
    }

    /**
     * returns all rooms
     * @return all rooms {@link Streamable<Room>}
     */
    public Streamable<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    /**
     * returns the room with the given id
     * @param id the id {@link Long}
     * @return the room with the id {@link Room}
     */
    public Room findRoomById(long id) {
        return roomRepository.findById(id).orElse(null);
    }

    /**
     * Returns all rooms the user is in
     * @param userId the id of the user {@link Long}
     * @return all rooms the user is in {@link Streamable<Room>}
     */
    public Streamable<Room> findAllRoomsOfUser(long userId) {
        User user = userManagement.findById(userId);
        if (user == null) {
            System.out.println("User not found");
            return null;
        }
        // get all -> filter by user -> map to room
        return participantsRepository.findAll().filter(p -> p.getUser().equals(user)).map(p -> p.getRoom());
    }

    /**
     * Returns all users in a room
     * @param roomId the id of the room {@link Long}
     * @return all users in a room {@link Streamable<User>}
     */
    public Streamable<User> findAllUsersInRoom(long roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room == null) {
            System.out.println("Room not found");
            return null;
        }
        return participantsRepository.findAll().filter(p -> p.getRoom().equals(room)).map(p -> p.getUser());
    }

    /**
     * Returns the first room with the given name
     * @param name the name {@link String}
     * @return the first room with the given name {@link Room}
     */
    public Room findRoomByName(String name) {
        Streamable<Room> rooms = roomRepository.findAll().filter(r -> r.getName().equals(name));
        if (!rooms.isEmpty()) {
            return rooms.toList().get(0);
        }
        return null;
    }

    /**
     * Returns all chat messages of a room
     * @param name the name of the room {@link String}
     * @return all chat messages of a room {@link Streamable<ChatMessage>}
     */
    public Streamable<ChatMessage> findAllMessagesInRoom(String name) {
        Room room = findRoomByName(name);
        if (room == null) {
            System.out.println("CommunicationManagement: Room not found");
            return null;
        }
        return chatMessageRepository.findAll().filter(m -> m.getRoom().equals(room));
    }

    /**
     * removes the given user from every room he is in and all messages he has sent
     * @param user
     */
    public void removeEverywhere(User user) {
        Assert.notNull(user, "user must not be null");
        participantsRepository.deleteAll(participantsRepository.findAll().filter(p -> p.getUser().equals(user)));
        chatMessageRepository.deleteAll(chatMessageRepository.findAll().filter(m -> m.getSender().equals(user)));
    }
}
