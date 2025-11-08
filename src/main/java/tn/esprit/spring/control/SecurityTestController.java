package tn.esprit.spring.control;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security-test")
public class SecurityTestController {

   // ❌ INJECTION SQL TRÈS EXPLICITE
@GetMapping("/login-unsafe")
public String loginUnsafe(@RequestParam String username, @RequestParam String password) {
    // Simulation d'une connexion très vulnérable
    String sql = "SELECT * FROM users WHERE username = '" + username + 
                 "' AND password = '" + password + "'";
    
    // Simulation d'exécution (très dangereux en réel)
    if (username.equals("admin") && password.equals("' OR '1'='1")) {
        return "Accès admin granted! SQL: " + sql;
    }
    return "Login failed. Query: " + sql;
}

    // ❌ TEST : Secrets exposés
    private String dbPassword = "admin123";
    private String apiKey = "sk_live_1234567890abcdef";

    // ❌ XSS TRÈS DANGEREUX
@GetMapping("/comment-unsafe")
public String commentUnsafe(@RequestParam String comment) {
    // Retour direct sans échappement
    return "<div class='comment'>" + comment + 
           "<script>alert('XSS Attack!')</script></div>";
}

// ❌ XSS AVEC COOKIE THEFT
@GetMapping("/profile-unsafe")
public String profileUnsafe(@RequestParam String username) {
    return "<h1>Profile: " + username + 
           "</h1><img src='x' onerror='stealCookies()'>";
}
}