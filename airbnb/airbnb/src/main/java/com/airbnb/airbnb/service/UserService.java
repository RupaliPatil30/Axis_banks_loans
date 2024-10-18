package com.airbnb.airbnb.service;

import com.airbnb.airbnb.Repository.PropertyUserRepository;
import com.airbnb.airbnb.dto.LoginDto;
import com.airbnb.airbnb.dto.PropertyUserDto;
import com.airbnb.airbnb.entity.PropertyUser;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
      private PropertyUserRepository userRepository;
      private JWTService jwtService;

    public UserService(PropertyUserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public PropertyUser addUser(PropertyUserDto propertyUserDto) {

        PropertyUser user = new PropertyUser();
        user.setFirstName(propertyUserDto.getFirstName());
        user.setLastName(propertyUserDto.getLastName());
        user.setUsername(propertyUserDto.getUsername());
        user.setUsername(propertyUserDto.getUsername());
        user.setEmail(propertyUserDto.getEmail());
        user.setPassword(BCrypt.hashpw(propertyUserDto.getPassword(), BCrypt.gensalt()));
        user.setUserRole(propertyUserDto.getUserRole());
        PropertyUser savedUser = userRepository.save(user);
        return savedUser;
    }

        public String verifyLogin(LoginDto loginDto) {
        Optional<PropertyUser>opUser=  userRepository.findByUsername(loginDto.getUsername());
        if(opUser.isPresent()){
            PropertyUser propertyUser=opUser.get();
         if (BCrypt.checkpw(loginDto.getPassword(),propertyUser.getPassword()));
     return   jwtService.generateToken( propertyUser);



        }
        return null;

    }
}
