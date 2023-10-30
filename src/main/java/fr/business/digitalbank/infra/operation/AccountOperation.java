package fr.business.digitalbank.infra.operation;

import fr.business.digitalbank.infra.account.BankAccount;
import fr.business.digitalbank.infra.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private double amount;
    @Enumerated(value = EnumType.STRING)
    private OperationType operationType;
    @ManyToOne
    private BankAccount bankAccount;
}
