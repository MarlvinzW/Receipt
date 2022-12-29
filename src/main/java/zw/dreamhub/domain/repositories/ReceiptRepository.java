package zw.dreamhub.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.dreamhub.domain.models.Receipt;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, String> {
    Set<Receipt> findByDateCreatedBetween(LocalDateTime dateCreatedStart, LocalDateTime dateCreatedEnd);
}