package auca.example.smartspend.model;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name="receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private String vendor;
    private String description;
    private int amount;
    private LocalDate recordedDate;

    @ManyToOne
    @JoinColumn(name="spend_request_id")
    private SpendRequest spendRequest;

    public Receipt() {
    }

    public Receipt(String vendor, String description, int amount, LocalDate recordedDate, SpendRequest spendRequest) {
        this.vendor = vendor;
        this.description = description;
        this.amount = amount;
        this.recordedDate = recordedDate;
        this.spendRequest = spendRequest;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(LocalDate recordedDate) {
        this.recordedDate = recordedDate;
    }

    public SpendRequest getSpendRequest() {
        return spendRequest;
    }

    public void setSpendRequest(SpendRequest spendRequest) {
        this.spendRequest = spendRequest;
    }
}