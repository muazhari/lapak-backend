package com.muazhari.findcomputerws.api;

import com.muazhari.findcomputerws.contracts.LoginContract;
import com.muazhari.findcomputerws.contracts.RegisterContract;
import com.muazhari.findcomputerws.models.User;
import com.muazhari.findcomputerws.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> authLogin(@RequestBody LoginContract loginContract) {
        return authService.login(loginContract);
    }

    @PostMapping("/register")
    public ResponseEntity<User> authRegister(@RequestBody RegisterContract registerContract) {
        return authService.register(registerContract);
    }
}
