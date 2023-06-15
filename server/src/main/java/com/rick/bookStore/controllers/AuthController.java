package com.rick.bookStore.controllers;

import com.rick.bookStore.security.AccountCredentialsVO;
import com.rick.bookStore.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Endpoint")
public class AuthController {
    @Autowired
    AuthService authService;
    @Operation(summary = "Authenticates a user and returns a token")
    @PostMapping(value = "/signin")
    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(@RequestBody AccountCredentialsVO data){
        if(checkIfParamsIsNull(data)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }

        var token = authService.signin(data);
        if(token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }

        return token;
    }



    @Operation(summary = "Refresh token for authenticated user and returns a token")
    @PutMapping(value = "/refresh/{username}")
    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(@PathVariable("username") String username,
                                       @RequestHeader("Authorization") String refreshToken){
        if(checkIfParamsIsNotNull(username, refreshToken)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }

        var token = authService.refreshToken(username,refreshToken);
        if(token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }

        return token;
    }

    private static boolean checkIfParamsIsNotNull(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() || username == null || username.isBlank();
    }


    private static boolean checkIfParamsIsNull(AccountCredentialsVO data) {
        return data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword() == null || data.getPassword().isBlank();
    }
}
