package festivalmanager.communication;

import org.salespointframework.core.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserManagement;

@Order(51)
@Component
public class ParticipantsDataInitzializer implements DataInitializer {

    private final CommunicationManagement communicationManagement;
    private final UserManagement userManagement;

    public ParticipantsDataInitzializer(CommunicationManagement communicationManagement,
            UserManagement userManagement) {
        Assert.notNull(communicationManagement, "communicationManagement must not be null!");
        Assert.notNull(userManagement, "userManagement must not be null!");
        this.communicationManagement = communicationManagement;
        this.userManagement = userManagement;
    }

    @Override
    public void initialize() {
        Streamable<User> users = userManagement.findAll();
        Streamable<Room> rooms = communicationManagement.findAllRooms();

        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        if (users.toList().size() < 2) {
            System.out.println("Not enough users found.");
            return;
        }
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
            return;
        }

        User user1 = users.toList().get(0);
        User user2 = users.toList().get(1);
        Room room1 = rooms.toList().get(0);

        communicationManagement.joinRoom(user1, room1, "rw");
        communicationManagement.joinRoom(user2, room1, "rw");

    }

}
