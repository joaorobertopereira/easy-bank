package br.com.helpcsistemas.easybank.controller;

import br.com.helpcsistemas.easybank.model.Customer;
import br.com.helpcsistemas.easybank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        Customer newCustomer;
        ResponseEntity response = null;

        if (Objects.nonNull(customerRepository.findByEmail(customer.getEmail()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already registered");
        }

        try
        {
            String hashPassword = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPassword);
            newCustomer = customerRepository.save(customer);
            if (newCustomer.getId() > 0) {
                response = ResponseEntity.status(HttpStatus.CREATED).body("User registered");
            }
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR). body("An exception occured. error: "+e.getMessage());

        }

        return response;
    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        return customerRepository.findByEmail(authentication.getName());
    }
}
