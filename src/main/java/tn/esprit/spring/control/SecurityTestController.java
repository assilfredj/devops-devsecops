package tn.esprit.spring.control;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security-test")
public class SecurityTestController {

    // ❌ TEST : Injection SQL
    @GetMapping("/unsafe-sql")
    public String unsafeSql(@RequestParam String username) {
        String query = "SELECT * FROM users WHERE username = '" + username + "'";
        return "Query: " + query;
    }

    // ❌ TEST : Secrets exposés
    private String dbPassword = "admin123";
    private String apiKey = "sk_live_1234567890abcdef";

    // ❌ TEST : XSS potentiel
    @GetMapping("/welcome")
    public String welcome(@RequestParam String name) {
        return "<h1>Welcome " + name + "!</h1>";
    }
}