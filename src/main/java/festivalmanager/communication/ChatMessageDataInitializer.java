package festivalmanager.communication;

import org.salespointframework.core.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserManagement;

/**
 * @author Conrad
 * ChatMessageDataInitializer class
 * initzializes the database with some chat messages
 */
@Order(52)
@Component
public class ChatMessageDataInitializer implements DataInitializer {

    private final CommunicationManagement communicationManagement;
    private final UserManagement userManagement;

    /**
     * constructor
     * @param communicationManagement {@link CommunicationManagement}
     * @param userManagement {@link UserManagement}
     */
    public ChatMessageDataInitializer(CommunicationManagement communicationManagement, UserManagement userManagement) {
        Assert.notNull(communicationManagement, "communicationManagement must not be null!");
        Assert.notNull(userManagement, "userManagement must not be null!");
        this.communicationManagement = communicationManagement;
        this.userManagement = userManagement;
    }

    /**
     * initzializes the database with some chat messages
     */
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

        User system = userManagement.findByName("SYSTEM");
        if (system == null) {
            System.out.println("initMessages: system not found");
            return;
        }

        communicationManagement.sendMessage(system, "Hello World from System", room);
    }

}
