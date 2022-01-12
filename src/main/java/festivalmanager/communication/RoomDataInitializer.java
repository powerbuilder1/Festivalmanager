package festivalmanager.communication;

import java.util.List;

import org.salespointframework.core.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Conrad
 * RoomDataInitializer is a class that adds dummy rooms
 */
@Order(50)
@Component
public class RoomDataInitializer implements DataInitializer {

    private final CommunicationManagement communicationManagement;

    /**
     * constructor
     * @param communicationManagement {@link CommunicationManagement}
     */
    public RoomDataInitializer(CommunicationManagement communicationManagement) {
        Assert.notNull(communicationManagement, "communicationManagement must not be null!");
        this.communicationManagement = communicationManagement;
    }

    /**
     * @see org.salespointframework.core.DataInitializer#initialize()
     * initialize the dummy rooms
     */
    @Override
    public void initialize() {
        List.of("Catering", "public", "Planning").forEach(communicationManagement::createRoom);
    }

}
