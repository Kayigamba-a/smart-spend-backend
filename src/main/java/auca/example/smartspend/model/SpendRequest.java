package auca.example.smartspend.model;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.*;

@Entity 
@Table(name="spend_request")
public class SpendRequest {

    @Id 
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private LocalDate requestDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private SpendStatus status;

    private String purpose;
    private int requestedAmount;

    @ManyToOne 
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="finance_officer_id")
    private FinanceOfficer financeOfficer;

    public SpendRequest() {
    }

    public SpendRequest(LocalDate requestDate, SpendStatus status, String purpose, int requestedAmount,
            Employee employee, FinanceOfficer financeOfficer) {
        this.requestDate = requestDate;
        this.status = status;
        this.purpose = purpose;
        this.requestedAmount = requestedAmount;
        this.employee = employee;
        this.financeOfficer = financeOfficer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public SpendStatus getStatus() {
        return status;
    }

    public void setStatus(SpendStatus status) {
        this.status = status;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(int requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public FinanceOfficer getFinanceOfficer() {
        return financeOfficer;
    }

    public void setFinanceOfficer(FinanceOfficer financeOfficer) {
        this.financeOfficer = financeOfficer;
    }
    


}
