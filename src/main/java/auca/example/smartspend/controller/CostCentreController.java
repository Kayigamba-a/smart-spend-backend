
package auca.example.smartspend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.example.smartspend.model.CostCentre;
import auca.example.smartspend.service.CostCentreService;

@RestController
@RequestMapping("/api/cost-centres")
public class CostCentreController {

    private final CostCentreService costCentreService;

    public CostCentreController(CostCentreService service) {
        this.costCentreService = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CostCentre>> getAllCostCentres() {

        return new ResponseEntity<>(
            costCentreService.getAllCostCentres(),
            HttpStatus.OK
        );
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveCostCentre(@RequestBody CostCentre costCentre) {

        costCentreService.saveCostCentre(costCentre);

        return new ResponseEntity<>(
            "Cost centre saved successfully",
            HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCostCentreById(@PathVariable UUID id) {

        CostCentre costCentre = costCentreService.getCostCentreById(id);

        if (costCentre != null) {
            return new ResponseEntity<>(costCentre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                "The cost centre with that id does not exist",
                HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCostCentre(
            @PathVariable UUID id,
            @RequestBody CostCentre costCentre) {

        CostCentre updatedCostCentre =
            costCentreService.updateCostCentre(id, costCentre);

        if (updatedCostCentre != null) {
            return new ResponseEntity<>(
                "Cost centre updated successfully",
                HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                "The cost centre with that id does not exist",
                HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCostCentre(@PathVariable UUID id) {

        boolean deleted = costCentreService.deleteCostCentre(id);

        if (deleted) {
            return new ResponseEntity<>(
                "Cost centre deleted successfully",
                HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                "The cost centre with that id does not exist",
                HttpStatus.NOT_FOUND
            );
        }
    }
}
