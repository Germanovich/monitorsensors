package com.germanovich.monitorsensors.controller.authentication;

import com.germanovich.monitorsensors.controller.security.util.AuthenticationHelper;
import com.germanovich.monitorsensors.controller.security.util.TokenHelper;
import com.germanovich.monitorsensors.dto.token.AuthenticationResponse;
import com.germanovich.monitorsensors.dto.user.UserDto;
import com.germanovich.monitorsensors.service.security.TokenRepository;
import com.germanovich.monitorsensors.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authorization")
@CrossOrigin
public class AuthenticationController {

    private final UserService userService;
    private final TokenHelper tokenHelper;
    private final TokenRepository tokenRepository;

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody final UserDto userDto) {
        UserDetails userDetails = userService.loadUserByLoginAndPassword(userDto.getLogin(), userDto.getPassword());

        String token = new AuthenticationResponse(tokenHelper.generateToken(userDetails.getUsername())).getToken();
        tokenRepository.addToken(userDto.getLogin(), token);
        return new AuthenticationResponse(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> removeAuthenticationToken() {
        tokenRepository.removeToken(AuthenticationHelper.getAuthenticationLogin());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
