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
        User user = userManagement.findByName("manager");
        if (user == null) {
            System.out.println("initMessages: manager not found");
            return;
        }
        Room room = communicationManagement.findRoomByName("public");
        if (room == null) {
            System.out.println("initMessages: room 'public' not found");
            return;
        }

        communicationManagement.sendMessage(user, "Message system is working now!", room);
    }

}
