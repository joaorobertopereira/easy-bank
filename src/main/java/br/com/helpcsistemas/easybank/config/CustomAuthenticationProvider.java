package br.com.helpcsistemas.easybank.config;

import br.com.helpcsistemas.easybank.model.Customer;
import br.com.helpcsistemas.easybank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Customer customer = customerRepository.findByEmail(username);
        if (Objects.nonNull(customer)) {
            if (passwordEncoder.matches(password, customer.getPwd())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                GrantedAuthority authority = new SimpleGrantedAuthority(customer.getRole().name());
                authorities.add(authority);
                log.info("Login Success. Authentication Provider.");
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            } else {
                log.error("Invalid Password!");
                throw new BadCredentialsException("Invalid Password");
            }
        } else {
            log.error("User not found");
            throw new BadCredentialsException("User not found : "+ username);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
