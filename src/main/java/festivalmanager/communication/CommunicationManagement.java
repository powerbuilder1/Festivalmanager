package festivalmanager.communication;

import javax.validation.constraints.AssertTrue;

import org.springframework.util.Assert;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserManagement;

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

        return null;
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
}
