package com.mateus.config.securityConfig;

import com.mateus.entities.Customer;
import com.mateus.repositories.CustomerRepository;
import com.mateus.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final CustomerRepository customerRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenFromHeader = getTokenFromHeader(request);

        boolean tokenValid = tokenService.isTokenValid(tokenFromHeader);

        if(tokenValid){
            this.authenticate(tokenFromHeader);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")){
            return token.substring(7, token.length());
        }
        return null;
    }

    private void authenticate(String tokenFromHeader) {
        Long customerId = tokenService.getIdFromToken(tokenFromHeader);

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(customer, null, customer.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

}
