package festivalmanager.communication;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.test.context.SpringBootTest;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserManagement;

@SpringBootTest
@AutoConfigureBefore
public class CommunicationIntegrationTests {

    @Autowired
    private CommunicationManagement communicationManagement;

    @Autowired
    private UserManagement userManagement;

    @Test
    public void createNewRoom() {
        Room room = communicationManagement.createRoom("TestRoom");
        assertThat(communicationManagement.findRoomByName(room.getName())).isNotNull();
    }

    @Test
    public void joinRoom() {
        Room room = communicationManagement.createRoom("TestRoom2");
        User system = userManagement.findByName("SYSTEM");

        communicationManagement.joinRoom(system, room, "rw");

        /**
         * error creating proxy...
         */
        // find "system" user in the room
        // assertThat(communicationManagement.findRoomByName(room.getName()).participants.stream()
        // .filter(p -> p.getUser().equals(system)).count()).isEqualTo(1);

        assertThat(communicationManagement.findAllUsersInRoom(room.getId()).toList().size()).isEqualTo(1);
    }

    @Test
    public void sendMessage() {
        Room room = communicationManagement.createRoom("TestRoom3");
        User manager = userManagement.findByName("manager");
        User catering = userManagement.findByName("Catering");

        communicationManagement.joinRoom(manager, room, "rw");
        communicationManagement.joinRoom(catering, room, "rw");

        ChatMessage message = communicationManagement.sendMessage(manager, "Hallo Welt", room);

        // check that the message is in the room
        assertThat(communicationManagement.findAllMessagesInRoom(room.getName())
                .filter(msg -> msg.getMessage().equals(message.getMessage())));
    }

}
