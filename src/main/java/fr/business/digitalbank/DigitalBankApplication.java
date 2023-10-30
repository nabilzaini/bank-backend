package fr.business.digitalbank;

import fr.business.digitalbank.infra.account.BankAccountRepository;
import fr.business.digitalbank.infra.account.CurrentAccount;
import fr.business.digitalbank.infra.account.SavingAccount;
import fr.business.digitalbank.infra.customer.Customer;
import fr.business.digitalbank.infra.customer.CustomerRepository;
import fr.business.digitalbank.infra.enums.AccountStatus;
import fr.business.digitalbank.infra.enums.OperationType;
import fr.business.digitalbank.infra.operation.AccountOperation;
import fr.business.digitalbank.infra.operation.OperationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository, OperationRepository operationRepository) {
        return args -> {
            Stream.of("Julien", "Nabil", "David").forEach(name -> {
                Customer c = new Customer();
                c.setName(name);
                c.setEmail(name + "gmail.com");
                customerRepository.save(c);
            });

            customerRepository.findAll().forEach(customer -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random() * 90000);
                currentAccount.setCreatedDate(new Date());
                currentAccount.setOverDraft(1000);
                currentAccount.setAccountStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(customer);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setAccountStatus(AccountStatus.CREATED);
                savingAccount.setBalance(Math.random() * 10000);
                savingAccount.setCreatedDate(new Date());
                savingAccount.setInterestRate(4.5);
                savingAccount.setCustomer(customer);
                bankAccountRepository.save(savingAccount);

            });

            bankAccountRepository.findAll().forEach(bankAccount -> {
                for (int i = 0; i < 5; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setAmount(Math.random() * 1000);
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setOperationType(Math.random() > 0.5 ? OperationType.CREDIT : OperationType.DEBIT);
                    accountOperation.setBankAccount(bankAccount);
                    operationRepository.save(accountOperation);
                }
            });

        };
    }
}
