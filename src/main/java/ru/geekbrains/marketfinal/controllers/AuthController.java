package ru.geekbrains.marketfinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.marketfinal.configurations.jwt.JwtProvider;
import ru.geekbrains.marketfinal.dtos.AuthRequestDto;
import ru.geekbrains.marketfinal.dtos.AuthResponseDto;
import ru.geekbrains.marketfinal.dtos.SignUpRequestDto;
import ru.geekbrains.marketfinal.entities.User;
import ru.geekbrains.marketfinal.services.UserService;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/signup")
    public String registerUser(@RequestBody SignUpRequestDto signUpRequest) {
        User user = new User();
        user.setPassword(signUpRequest.getPassword());
        user.setLogin(signUpRequest.getLogin());
        userService.saveUser(user);
        return "OK";
    }

    @PostMapping("/login")
    public AuthResponseDto auth(@RequestBody AuthRequestDto request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthResponseDto(token);
    }
}
