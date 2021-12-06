package festivalmanager.communication;

import com.mysema.commons.lang.Assert;

import org.springframework.data.util.Streamable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserManagement;

@Controller
public class CommunicationController {

    private final CommunicationManagement communicationManagement;
    private final UserManagement userManagement;

    public CommunicationController(CommunicationManagement communicationManagement, UserManagement userManagement) {
        Assert.notNull(communicationManagement, "communicationManagement must not be null");
        Assert.notNull(userManagement, "userManagement must not be null");
        this.communicationManagement = communicationManagement;
        this.userManagement = userManagement;
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userManagement.findByName(auth.getName());
    }

    @GetMapping("/news")
    String news(Model model) {
        model.addAttribute("news", communicationManagement.findAllMessagesInRoom("public"));
        model.addAttribute("titel", "News");
        return "news";
    }

    @PreAuthorize("hasAnyRole('PLANNING', 'CATERING', 'BOSS')")
    @GetMapping("/chat")
    String chat(Model model) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            System.out.println("/chat: User is null");
            return "redirect:/login";
        }

        // all chat rooms the current user is in
        Streamable<Room> rooms = communicationManagement.findAllRoomsOfUser(currentUser.getId());
        model.addAttribute("chats", rooms);

        return "chat";
    }

}
