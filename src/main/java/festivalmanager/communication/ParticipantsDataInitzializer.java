package festivalmanager.communication;

import org.salespointframework.core.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserManagement;

/**
 * @author Conrad
 * ParticipantsDataInitzializer is a class that adds the connection between the dummy users and the dummy rooms
 */
@Order(51)
@Component
public class ParticipantsDataInitzializer implements DataInitializer {

    private final CommunicationManagement communicationManagement;
    private final UserManagement userManagement;

    /**
     * constructor
     * @param communicationManagement {@link CommunicationManagement} 
     * @param userManagement {@link UserManagement}
     */ 
    public ParticipantsDataInitzializer(CommunicationManagement communicationManagement,
        UserManagement userManagement) {
        Assert.notNull(communicationManagement, "communicationManagement must not be null!");
        Assert.notNull(userManagement, "userManagement must not be null!");
        this.communicationManagement = communicationManagement;
        this.userManagement = userManagement;
    }

    /**
     * @see org.salespointframework.core.DataInitializer#initialize()
     * initialize the connection between the dummy users and the dummy rooms
     */
    @Override
    public void initialize() {
        // inits chat rooms
        initPublic();
        initCatering();
        initPlanning();
    }

    /**
     * initzializes the public message / info channel
     * the manager can send messages which can be seen on the front page
     */
    private void initPublic() {
        User manager = userManagement.findByName("manager");
        User system = userManagement.findByName("SYSTEM");

        if (manager == null) {
            System.out.println("initPublic: Manager not found");
            return;
        }
        if (system == null) {
            System.out.println("initPublic: SYSTEM not found");
            return;
        }

        Room room = communicationManagement.findRoomByName("public");
        if (room == null) {
            System.out.println("initPublic: Room not found");
            return;
        }
        communicationManagement.joinRoom(manager, room, "rw");
        communicationManagement.joinRoom(system, room, "r");
    }

    /**
     * initzializes the catering message
     * the manager and the catering staff can send messages in this group chat
     */
    private void initCatering() {
        User manager = userManagement.findByName("manager");
        Streamable<User> catering = userManagement.findAll()
                .filter(user -> user.getUserAccount().hasRole(UserManagement.CATERING_ROLE));

        if (manager == null) {
            System.out.println("initCatering: Manager not found");
            return;
        }
        if (catering.isEmpty()) {
            System.out.println("initCatering: No catering personal found");
            return;
        }

        Room room = communicationManagement.findRoomByName("Catering");
        if (room == null) {
            System.out.println("initCatering: Room not found");
            return;
        }

        communicationManagement.joinRoom(manager, room, "rw");
        for (User user : catering) {
            communicationManagement.joinRoom(user, room, "rw");
        }
    }

    /**
     * initzializes the planning message
     * the manager and the planning staff can send messages in this group chat
     */
    private void initPlanning() {
        User manager = userManagement.findByName("manager");
        Streamable<User> planning = userManagement.findAll()
                .filter(user -> user.getUserAccount().hasRole(UserManagement.PLANNING_ROLE));

        if (manager == null) {
            System.out.println("initCatering: Manager not found");
            return;
        }
        if (planning.isEmpty()) {
            System.out.println("initCatering: No catering personal found");
            return;
        }

        Room room = communicationManagement.findRoomByName("Planning");
        if (room == null) {
            System.out.println("initCatering: Room not found");
            return;
        }

        communicationManagement.joinRoom(manager, room, "rw");
        for (User user : planning) {
            communicationManagement.joinRoom(user, room, "rw");
        }
    }

}
