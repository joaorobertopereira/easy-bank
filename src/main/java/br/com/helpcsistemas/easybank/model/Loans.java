package br.com.helpcsistemas.easybank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_number")
    private Integer id;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "start_dt")
    private Date startDt;
    @Column(name = "loan_type")
    private String loanType;
    @Column(name = "total_loan")
    private Integer totalLoan;
    @Column(name = "amount_paid")
    private Integer amountPaid;
    @Column(name = "outstanding_amount")
    private Integer outstandingAmount;
    @Column(name = "create_dt")
    private String createDt;
}
