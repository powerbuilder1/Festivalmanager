package festivalmanager.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    /**
     * Default constructor
     */
    public ErrorController() {
        // default constructor
    }

    @GetMapping("/error")
    String error(HttpServletRequest request, Model model) {
        return "error";
    }

}