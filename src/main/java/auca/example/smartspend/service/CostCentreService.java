
package auca.example.smartspend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.example.smartspend.model.CostCentre;
import auca.example.smartspend.repository.CostCentreRepository;

@Service
public class CostCentreService {

    private final CostCentreRepository costCentreRepository;

    public CostCentreService(CostCentreRepository repository) {
        this.costCentreRepository = repository;
    }

    public List<CostCentre> getAllCostCentres() {
        return costCentreRepository.findAll();
    }

    public CostCentre saveCostCentre(CostCentre costCentre) {
        return costCentreRepository.save(costCentre);
    }

    public CostCentre getCostCentreById(UUID id) {
        return costCentreRepository.findById(id).orElse(null);
    }

    public CostCentre updateCostCentre(UUID id, CostCentre costCentre) {

        CostCentre existingCostCentre = getCostCentreById(id);

        if (existingCostCentre != null) {

            existingCostCentre.setCentreNumber(costCentre.getCentreNumber());
            existingCostCentre.setName(costCentre.getName());

            return costCentreRepository.save(existingCostCentre);
        }

        return null;
    }

    public boolean deleteCostCentre(UUID id) {

        CostCentre costCentre = getCostCentreById(id);

        if (costCentre != null) {
            costCentreRepository.delete(costCentre);
            return true;
        }

        return false;
    }
}
