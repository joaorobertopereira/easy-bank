package br.com.helpcsistemas.easybank.service;

import br.com.helpcsistemas.easybank.model.Customer;
import br.com.helpcsistemas.easybank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EasyBankUserDetails implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (Objects.nonNull(customer)) {
            GrantedAuthority authority = new SimpleGrantedAuthority(customer.getRole().name());
            authorities.add(authority);
        } else {
            throw new RuntimeException("User not found : "+ username);
        }
        return new User(customer.getEmail(), customer.getPwd(), authorities);
    }

}
