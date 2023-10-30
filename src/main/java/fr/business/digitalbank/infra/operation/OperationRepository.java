package fr.business.digitalbank.infra.operation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<AccountOperation, Long> {
}
