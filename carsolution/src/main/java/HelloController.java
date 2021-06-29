import org.springframework.web.bind.annotation.GetMapping;

public class HelloController {
    @GetMapping("/")
    public String start() {
        return "Helló";
    }
}
