package com.managermoneyapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "transfers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transfer {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "account_origin", referencedColumnName = "id")
    private Account accountOrigin;

    @ManyToOne
    @JoinColumn(name = "account_destination", referencedColumnName = "id")
    private Account accountDestination;

    @Column(name = "date_time", nullable = false)
    private String date_time;
}
