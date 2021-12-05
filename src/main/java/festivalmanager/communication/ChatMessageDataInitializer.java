package festivalmanager.communication;

import org.salespointframework.core.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserManagement;

@Order(52)
@Component
public class ChatMessageDataInitializer implements DataInitializer {

    private final CommunicationManagement communicationManagement;
    private final UserManagement userManagement;

    public ChatMessageDataInitializer(CommunicationManagement communicationManagement, UserManagement userManagement) {
        Assert.notNull(communicationManagement, "communicationManagement must not be null!");
        Assert.notNull(userManagement, "userManagement must not be null!");
        this.communicationManagement = communicationManagement;
        this.userManagement = userManagement;
    }

    @Override
    public void initialize() {
        Streamable<User> users = userManagement.findAll();
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        User user = users.toList().get(0);

        Streamable<Room> rooms = communicationManagement.findAllRoomsOfUser(user.getId());
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
            return;
        }

        Room room = rooms.toList().get(0);

        ChatMessage msg = communicationManagement.sendMessage(user.getId(), "Hallo Welt!", room.getId());
        if (msg != null) {
            System.out.println(msg.toString());
        }

    }

}
