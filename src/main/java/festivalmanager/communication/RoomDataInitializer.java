package festivalmanager.communication;

import java.util.List;

import org.salespointframework.core.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Order(50)
@Component
public class RoomDataInitializer implements DataInitializer {

    private final CommunicationManagement communicationManagement;

    public RoomDataInitializer(CommunicationManagement communicationManagement) {
        Assert.notNull(communicationManagement, "communicationManagement must not be null!");
        this.communicationManagement = communicationManagement;
    }

    @Override
    public void initialize() {
        List.of("Catering", "public", "Planning").forEach(communicationManagement::createRoom);
    }

}
