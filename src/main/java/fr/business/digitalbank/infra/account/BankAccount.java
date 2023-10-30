package fr.business.digitalbank.infra.account;

import fr.business.digitalbank.infra.customer.Customer;
import fr.business.digitalbank.infra.enums.AccountStatus;
import fr.business.digitalbank.infra.operation.AccountOperation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 4)
public class BankAccount {
    @Id
    private String id;
    private double balance;
    private Date createdDate;
    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount")
    private List<AccountOperation> accountOperations;
}
