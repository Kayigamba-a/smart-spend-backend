package auca.example.smartspend.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import auca.example.smartspend.model.*;

public interface ReceiptRepository extends JpaRepository<Receipt, UUID>{

}