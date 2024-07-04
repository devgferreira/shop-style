package com.gabriel.ferreira.ms_customer.appresentation;

import com.gabriel.ferreira.ms_customer.domain.model.user.User;
import com.gabriel.ferreira.ms_customer.domain.model.user.login.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
public class AuthenticationController {
    private final AuthenticationManager _authenticationManager;

    public AuthenticationController(AuthenticationManager authenticationManager) {
        _authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody Login login ){

        var userNamePassword = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        var auth = _authenticationManager.authenticate(userNamePassword);
        return new ResponseEntity<>("Login feito com sucesso", HttpStatus.OK);
    }
}
