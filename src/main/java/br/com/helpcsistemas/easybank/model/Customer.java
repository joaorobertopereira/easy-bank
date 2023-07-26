package br.com.helpcsistemas.easybank.model;

import br.com.helpcsistemas.easybank.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private Role role;
    @Column(name = "create_dt")
    private String createDt;

}
