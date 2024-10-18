package com.airbnb.airbnb.controller;

import com.airbnb.airbnb.dto.LoginDto;
import com.airbnb.airbnb.dto.PropertyUserDto;
import com.airbnb.airbnb.dto.TokenResponse;
import com.airbnb.airbnb.entity.PropertyUser;
import com.airbnb.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.introspector.Property;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
@PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody PropertyUserDto propertyUserDto) {

    PropertyUser propertyUser = userService.addUser(propertyUserDto);
    if (propertyUser != null) {

        return new ResponseEntity<>("Registration is successful", HttpStatus.CREATED);

    }

    return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);

}
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        String token =userService.verifyLogin(loginDto);
        if (token!=null){
            TokenResponse tokenResponse =new TokenResponse();
            tokenResponse.setToken(token);

            return new ResponseEntity<>(token,HttpStatus.OK);

        }
        return new ResponseEntity<>("In valid credentials",HttpStatus.UNAUTHORIZED);
}

}
