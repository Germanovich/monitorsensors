package com.germanovich.monitorsensors.controller.security.filter;

import com.germanovich.monitorsensors.controller.security.util.TokenHelper;
import com.germanovich.monitorsensors.service.security.TokenRepository;
import com.germanovich.monitorsensors.service.user.UserService;
import com.hermanovich.accountingsystem.util.MessageForUser;
import com.sun.istack.Nullable;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
public class SecurityFilter extends BasicAuthenticationFilter {

    public static final String BEARER = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";

    private final UserService userService;
    private final TokenHelper tokenHelper;
    private final TokenRepository tokenRepository;

    public SecurityFilter(final UserService userService,
                          final TokenHelper tokenHelper,
                          final AuthenticationManager authenticationManager,
                          final TokenRepository tokenRepository) {
        super(authenticationManager);
        this.userService = userService;
        this.tokenHelper = tokenHelper;
        this.tokenRepository = tokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var token = getTokenFromRequest(request);
        if (tokenHelper.validateToken(token)) {
            var login = tokenHelper.getLoginFromToken(token);

            if (tokenRepository.getToken(login) == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                log.info(MessageForUser.TOKEN_IS_INVALID.get());
                return;
            }
            var userDetails = userService.loadUserByUsername(login);
            var authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null,
                    userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        var bearer = request.getHeader(AUTHORIZATION);

        if (bearer != null && bearer.startsWith(BEARER)) {
            return bearer.substring(BEARER.length());
        }
        return null;
    }
}
