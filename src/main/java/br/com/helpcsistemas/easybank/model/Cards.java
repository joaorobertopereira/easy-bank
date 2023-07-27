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
@Table(name = "cards")
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Integer cardId;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "total_limit")
    private Integer totalLimit;
    @Column(name = "amount_used")
    private Integer amountUsed;
    @Column(name = "available_amount")
    private Integer availableAmount;
    @Column(name = "create_dt")
    private Date createDt;
}
