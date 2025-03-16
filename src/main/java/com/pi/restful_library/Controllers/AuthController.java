package com.pi.restful_library.Controllers;


import com.pi.restful_library.Services.AuthService;
import com.pi.restful_library.model.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Endpoint to register a new member.
     *
     * @param member The member details to register.
     * @return The registered member.
     */
    @PostMapping("/register")
    public ResponseEntity<Members> register(@RequestBody Members member) {
        Members registeredMember = authService.register(member);
        return ResponseEntity.ok(registeredMember);
    }

    /**
     * Endpoint to log in a member.
     *
     * @param member The member credentials (email and password).
     * @return A JWT token if login is successful.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Members member) {
        String token = authService.login(member);
        return ResponseEntity.ok(token);
    }
}