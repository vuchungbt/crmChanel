package restful.Controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("/login");
        return "index.html";
    }
    @GetMapping(value = "/default")
    public String defaultIndex(HttpServletRequest request, HttpServletResponse response) {
        return "crm-index.html";
    }
    @GetMapping(value = "/task")
    public String defaultTask(HttpServletRequest request, HttpServletResponse response) {
        return "crm-task.html";
    }


}
