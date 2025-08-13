package com.mayank.mutualFund.authentication.controller;


import com.mayank.mutualFund.authentication.dto.AuthResponseDto;
import com.mayank.mutualFund.authentication.dto.LoginRequestDto;
import com.mayank.mutualFund.authentication.dto.RegisterRequestDto;
import com.mayank.mutualFund.authentication.dto.VerifyUserRequestDto;
import com.mayank.mutualFund.authentication.entity.User;
import com.mayank.mutualFund.authentication.mapper.Mapper;
import com.mayank.mutualFund.authentication.service.AuthenticationService;
import com.mayank.mutualFund.authentication.service.JwtService;
import com.mayank.mutualFund.authentication.service.OtpService;
import com.mayank.mutualFund.authentication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
public class AuthController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final Mapper<User,RegisterRequestDto> registerRequestDtoMapper;
    private final Mapper<User, LoginRequestDto> loginRequestDtoMapper;
    private final AuthenticationService authenticationService;
    private final OtpService otpService;

    public AuthController(UserService userService, UserDetailsService userDetailsService, JwtService jwtService, Mapper<User, RegisterRequestDto> registerRequestDtoMapper, Mapper<User, LoginRequestDto> loginRequestDtoMapper, AuthenticationService authenticationService, OtpService otpService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.registerRequestDtoMapper = registerRequestDtoMapper;
        this.loginRequestDtoMapper = loginRequestDtoMapper;
        this.authenticationService = authenticationService;
        this.otpService = otpService;
    }


    @PostMapping("/v1/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDto registerRequestDto){
        User user=registerRequestDtoMapper.convertToEntity(registerRequestDto);

        Optional<User> optionalUser=userService.getUserByEmail(user.getEmail());

        if(optionalUser.isPresent() && optionalUser.get().getEnabled()){
            return new ResponseEntity<>("Account Already Exist",HttpStatus.NOT_FOUND);
        }
        else {
            User savedUser=userService.saveUser(user);
            try {
                otpService.sendOtp(savedUser.getEmail(),savedUser.getVerificationCode());
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

        return  new ResponseEntity<>( HttpStatus.CREATED);
    }

    @PostMapping("/v1/auth/login")
    public ResponseEntity<AuthResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto){
        User user=loginRequestDtoMapper.convertToEntity(loginRequestDto);


        if(!userService.isExistsByEmail(user.getEmail())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            if (!authenticationService.isAuthentic(user)){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        AuthResponseDto authResponseDto=AuthResponseDto.builder()
                .token(jwtService.generateToken(userDetailsService.loadUserByUsername(user.getEmail())))
                .build();
        return  new ResponseEntity<>(authResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/v1/auth/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestBody VerifyUserRequestDto verifyUserRequestDto){
        try {
            authenticationService.verifyOtp(verifyUserRequestDto);
        } catch (RuntimeException e) {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Account Verified",HttpStatus.ACCEPTED);
    }



}
