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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("news", communicationManagement.findAllMessagesInRoom("public")
                .filter(msg -> msg.getSender().getName().equals("manager")));
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

    @PreAuthorize("hasAnyRole('PLANNING', 'CATERING', 'BOSS')")
    @GetMapping("/chat/room/{id}")
    String chatRoom(@PathVariable long id, Model model) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            System.out.println("/chat: User is null");
            return "redirect:/login";
        }
        model.addAttribute("you", currentUser);

        // all chat rooms the current user is in
        Streamable<Room> rooms = communicationManagement.findAllRoomsOfUser(currentUser.getId());
        model.addAttribute("chats", rooms);

        // fetch current chat room
        Room room = communicationManagement.findRoomById(id);
        model.addAttribute("room", room);

        // fetch all messages in the current chat room
        Streamable<ChatMessage> messages = communicationManagement.findAllMessagesInRoom(room.getName());
        model.addAttribute("messages", messages);

        return "chat";
    }

    @PreAuthorize("hasAnyRole('PLANNING', 'CATERING', 'BOSS')")
    @PostMapping("/chat/room/{id}")
    String chatRoom(@PathVariable long id, @RequestParam String message, RedirectAttributes redirectAttributes) {
        if (message.isBlank()) {
            redirectAttributes.addFlashAttribute("error_input", "Message cannot be empty");
            return "redirect:/chat/room/" + id;
        }
        // get current user
        User currentUser = getCurrentUser();
        // fetch current chat room
        Room room = communicationManagement.findRoomById(id);
        communicationManagement.sendMessage(currentUser, message, room);
        return "redirect:/chat/room/" + id;
    }
}
