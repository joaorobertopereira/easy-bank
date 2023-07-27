package br.com.helpcsistemas.easybank.model;

import br.com.helpcsistemas.easybank.model.enums.Authority;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;
    private String name;
    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;
    private String pwd;
    @Column(name = "create_dt")
    private String createDt;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="authorities")
    private Set<Integer> authorities = new HashSet<>(); ;

    public Set<Authority> getAuthorities() {
        return authorities.stream().map(Authority::toEnum).collect(Collectors.toSet());
    }

    public void addAuthority(Authority authority) {
        authorities.add(authority.getCod());
    }

    public Customer() {
        this.createDt = String.valueOf(new Date(System.currentTimeMillis()));
        addAuthority(Authority.USER);
    }

    public Customer(Integer id, String name, String email, String mobileNumber, String pwd) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.pwd = pwd;
        this.createDt = String.valueOf(new Date(System.currentTimeMillis()));
        addAuthority(Authority.USER);
    }
}
