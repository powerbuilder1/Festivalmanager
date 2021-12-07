package festivalmanager.communication;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserManagement;

@Service
@Transactional
public class CommunicationManagement {

    private final ChatMessageRepository chatMessageRepository;
    private final RoomRepository roomRepository;
    private final UserManagement userManagement;
    private final ParticipantsRepository participantsRepository;

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

    public Room createRoom(String name) {
        return roomRepository.save(new Room(name));
    }

    public ChatMessage sendMessage(User sender, String message, Room room) {
        Assert.notNull(sender, "sender must not be null");
        Assert.notNull(message, "message must not be null");
        Assert.notNull(room, "room must not be null");
        ChatMessage chatMessage = new ChatMessage(message, sender, room);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public ChatMessage sendMessage(long userId, String message, long roomId) {
        User sender = userManagement.findById(userId);
        Room room = roomRepository.findById(roomId).orElse(null);

        if (sender == null || room == null) {
            System.out.println("Sender or room not found");
            return null;
        }

        return sendMessage(sender, message, room);
    }

    public boolean joinRoom(User user, Room room, String access) {
        Assert.notNull(user, "user must not be null");
        Assert.notNull(room, "room must not be null");
        Assert.notNull(access, "access must not be null");
        Assert.isTrue(access.length() > 0, "access must not be empty");
        participantsRepository.save(new Participants(user, room, access));
        return true;
    }

    public boolean joinRoom(long userId, long roomId, String access) {
        User user = userManagement.findById(userId);
        Room room = roomRepository.findById(roomId).orElse(null);

        if (user == null || room == null) {
            System.out.println("User or room not found");
            return false;
        }
        return joinRoom(user, room, access);
    }

    public Streamable<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    public Room findRoomById(long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public Streamable<Room> findAllRoomsOfUser(long userId) {
        User user = userManagement.findById(userId);
        if (user == null) {
            System.out.println("User not found");
            return null;
        }
        // get all -> filter by user -> map to room
        return participantsRepository.findAll().filter(p -> p.getUser().equals(user)).map(p -> p.getRoom());
    }

    public Streamable<User> findAllUsersInRoom(long roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room == null) {
            System.out.println("Room not found");
            return null;
        }
        return participantsRepository.findAll().filter(p -> p.getRoom().equals(room)).map(p -> p.getUser());
    }

    public Room findRoomByName(String name) {
        Streamable<Room> rooms = roomRepository.findAll().filter(r -> r.getName().equals(name));
        if (!rooms.isEmpty()) {
            return rooms.toList().get(0);
        }
        return null;
    }

    public Streamable<ChatMessage> findAllMessagesInRoom(String name) {
        Room room = findRoomByName(name);
        if (room == null) {
            System.out.println("CommunicationManagement: Room not found");
            return null;
        }
        return chatMessageRepository.findAll().filter(m -> m.getRoom().equals(room));
    }
}
