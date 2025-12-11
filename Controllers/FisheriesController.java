package com.agri.agri_system.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agri.agri_system.model.Fish;
import com.agri.agri_system.repository.FishRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/fisheries")
@CrossOrigin(origins = "*")
public class FisheriesController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FishRepository fishRepository;

    private String normalizeBarangay(String barangay) {
        if (barangay == null) return "";
        return barangay.trim().toLowerCase().replace(" ", "_");
    }

    private String table() {
        return "farmers_info.fisheries";
    }

    
    private void calculateTotals(Fish fish) {
       
        double totalPondArea = fish.getTilapiaArea() + fish.getBangusArea() + 
                               fish.getHitoArea() + fish.getCrawfishArea();
        fish.setTotalPondArea(totalPondArea);
        
       
        double totalAvgProduction = fish.getTilapiaStocks() + fish.getBangusStocks() + 
                                    fish.getHitoStocks() + fish.getCrawfishStocks();
        fish.setTotalAvgProduction(totalAvgProduction);
        
        
        int totalFeedsUsed = fish.getTilapiaFeeds() + fish.getBangusFeeds() + 
                             fish.getCatfishFeeds() + fish.getCrawfishFeeds();
        fish.setTotalFeedsUsed(totalFeedsUsed);
    }

    @PostMapping("/fish/{barangay}")
    public ResponseEntity<?> saveFish(
            @PathVariable String barangay,
            @Valid @RequestBody Fish fish,
            BindingResult bindingResult
    ) {
       
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        String table = table();
        String normalized = normalizeBarangay(barangay);
        fish.setBarangay(normalized);

        
        Optional<Fish> existingFarmer = fishRepository.findByFarmerNameAndContact(
            fish.getFarmerName(), 
            fish.getContact()
        );
        
        if (existingFarmer.isPresent()) {
            String existingBarangay = existingFarmer.get().getBarangay();
            return ResponseEntity.status(409)
                    .body("Farmer already exists in " + existingBarangay + " !");
        }

        
        calculateTotals(fish);

        String columns = "farmer_name, contact, age, sex, " +
                "tilapia, bangus, hito, " +
                "tilapia_ponds, tilapia_area, tilapia_stocks, " +
                "bangus_ponds, bangus_area, bangus_stocks, " +
                "hito_ponds, hito_area, hito_stocks, " +
                "crawfish_ponds, crawfish_area, crawfish_stocks, " +
                "total_pond_area, total_avg_production, total_feeds_used, " +
                "tilapia_feeds, bangus_feeds, catfish_feeds, crawfish_feeds, " +
                "barangay";

        String[] cols = columns.split("\\s*,\\s*");
        int columnCount = cols.length;
        String placeholders = String.join(",", Collections.nCopies(columnCount, "?"));

        String sql = "INSERT INTO " + table + " (" + columns + ") VALUES (" + placeholders + ")";

        try {
            List<Object> params = new ArrayList<>();
            params.add(fish.getFarmerName());
            params.add(fish.getContact());
            params.add(fish.getAge());
            params.add(fish.getSex());
            params.add(fish.getTilapia());
            params.add(fish.getBangus());
            params.add(fish.getHito());
            params.add(fish.getTilapiaPonds());
            params.add(fish.getTilapiaArea());
            params.add(fish.getTilapiaStocks());
            params.add(fish.getBangusPonds());
            params.add(fish.getBangusArea());
            params.add(fish.getBangusStocks());
            params.add(fish.getHitoPonds());
            params.add(fish.getHitoArea());
            params.add(fish.getHitoStocks());
            params.add(fish.getCrawfishPonds());
            params.add(fish.getCrawfishArea());
            params.add(fish.getCrawfishStocks());
            params.add(fish.getTotalPondArea());
            params.add(fish.getTotalAvgProduction());
            params.add(fish.getTotalFeedsUsed());
            params.add(fish.getTilapiaFeeds());
            params.add(fish.getBangusFeeds());
            params.add(fish.getCatfishFeeds());
            params.add(fish.getCrawfishFeeds());
            params.add(fish.getBarangay());

            if (params.size() != columnCount) {
                return ResponseEntity.badRequest()
                        .body("Parameter/column mismatch: columns=" + columnCount + " params=" + params.size());
            }

            jdbcTemplate.update(sql, params.toArray());

            return ResponseEntity.ok("Saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("Failed to save data: " + e.getMessage());
        }
    }

    @GetMapping("/{barangay}")
    public List<Fish> getAllFish(@PathVariable String barangay) {
        String normalized = normalizeBarangay(barangay);
        String table = table();
        String sql = "SELECT * FROM " + table + " WHERE barangay = ?";

        try {
            return jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(Fish.class),
                    normalized);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{barangay}/{id}")
    public ResponseEntity<Fish> getFishById(
            @PathVariable String barangay,
            @PathVariable Long id
    ) {
        String normalized = normalizeBarangay(barangay);
        String table = table();
        String sql = "SELECT * FROM " + table + " WHERE id = ? AND barangay = ?";

        try {
            Fish fish = jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(Fish.class),
                    id, normalized
            );
            return ResponseEntity.ok(fish);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{barangay}/{id}")
    public ResponseEntity<String> deleteFish(
            @PathVariable String barangay,
            @PathVariable Long id
    ) {
        String normalized = normalizeBarangay(barangay);
        String table = table();
        String sql = "DELETE FROM " + table + " WHERE id = ? AND barangay = ?";

        try {
            int rows = jdbcTemplate.update(sql, id, normalized);

            if (rows > 0) return ResponseEntity.ok("Deleted successfully");
            else return ResponseEntity.notFound().build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("Failed to delete data: " + e.getMessage());
        }
    }

    @PutMapping("/{barangay}/{id}")
    public ResponseEntity<?> updateFish(
            @PathVariable String barangay,
            @PathVariable Long id,
            @Valid @RequestBody Fish fish,
            BindingResult bindingResult
    ) {
        
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        String normalized = normalizeBarangay(barangay);
        String table = table();

        fish.setBarangay(normalized);
        fish.setId(id);

        
        calculateTotals(fish);

        String sql = "UPDATE " + table + " SET " +
                "farmer_name = ?, contact = ?, age = ?, sex = ?, " +
                "tilapia = ?, bangus = ?, hito = ?, " +
                "tilapia_ponds = ?, tilapia_area = ?, tilapia_stocks = ?, " +
                "bangus_ponds = ?, bangus_area = ?, bangus_stocks = ?, " +
                "hito_ponds = ?, hito_area = ?, hito_stocks = ?, " +
                "crawfish_ponds = ?, crawfish_area = ?, crawfish_stocks = ?, " +
                "total_pond_area = ?, total_avg_production = ?, total_feeds_used = ?, " +
                "tilapia_feeds = ?, bangus_feeds = ?, catfish_feeds = ?, crawfish_feeds = ? " +
                "WHERE id = ? AND barangay = ?";

        try {
            int rows = jdbcTemplate.update(sql,
                    fish.getFarmerName(),
                    fish.getContact(),
                    fish.getAge(),
                    fish.getSex(),
                    fish.getTilapia(),
                    fish.getBangus(),
                    fish.getHito(),
                    fish.getTilapiaPonds(),
                    fish.getTilapiaArea(),
                    fish.getTilapiaStocks(),
                    fish.getBangusPonds(),
                    fish.getBangusArea(),
                    fish.getBangusStocks(),
                    fish.getHitoPonds(),
                    fish.getHitoArea(),
                    fish.getHitoStocks(),
                    fish.getCrawfishPonds(),
                    fish.getCrawfishArea(),
                    fish.getCrawfishStocks(),
                    fish.getTotalPondArea(),
                    fish.getTotalAvgProduction(),
                    fish.getTotalFeedsUsed(),
                    fish.getTilapiaFeeds(),
                    fish.getBangusFeeds(),
                    fish.getCatfishFeeds(),
                    fish.getCrawfishFeeds(),
                    id, normalized);

            if (rows > 0) {
                return ResponseEntity.ok("Updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to update data: " + e.getMessage());
        }
    }
}