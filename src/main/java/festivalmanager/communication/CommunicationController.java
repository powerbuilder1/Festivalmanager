package festivalmanager.communication;

import com.mysema.commons.lang.Assert;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunicationController {

    private final CommunicationManagement communicationManagement;

    public CommunicationController(CommunicationManagement communicationManagement) {
        Assert.notNull(communicationManagement, "communicationManagement must not be null");
        this.communicationManagement = communicationManagement;
    }

    @GetMapping("/news")
    String news(Model model) {
        model.addAttribute("news", communicationManagement.findAllMessagesInRoom("public"));
        model.addAttribute("titel", "News");
        return "news";
    }

}
