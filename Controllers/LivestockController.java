package com.agri.agri_system.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agri.agri_system.model.Livestock;
import com.agri.agri_system.repository.LivestockRepository;

@RestController
@RequestMapping("/api/livestock")
@CrossOrigin(origins = "*")
public class LivestockController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private LivestockRepository livestockRepository;

    private String normalizeBarangay(String barangay) {
        if (barangay == null) return "";
        return barangay.trim().toLowerCase().replace(" ", "_");
    }

    private String table() {
        return "farmers_info.livestock";
    }

    @PostMapping("/{barangay}")
    public ResponseEntity<String> saveLivestock(
            @PathVariable String barangay,
            @RequestBody Livestock livestock
    ) {
        String table = table();
        String normalized = normalizeBarangay(barangay);
        livestock.setBarangay(normalized);

        // ===== DEBUG LOGS =====
        System.out.println("=== LIVESTOCK DUPLICATE CHECK DEBUG ===");
        System.out.println("Checking farmer: [" + livestock.getFarmerName() + "]");
        System.out.println("Contact: [" + livestock.getContact() + "]");
        System.out.println("Target barangay: [" + normalized + "]");

        // ===== GLOBAL DUPLICATE CHECKING (ACROSS ALL BARANGAYS) =====
        Optional<Livestock> existingFarmer = livestockRepository.findByFarmerNameAndContact(
            livestock.getFarmerName(), 
            livestock.getContact()
        );
        
        System.out.println("Existing farmer found: " + existingFarmer.isPresent());
        
        if (existingFarmer.isPresent()) {
            String existingBarangay = existingFarmer.get().getBarangay();
            System.out.println("DUPLICATE DETECTED! Existing barangay: " + existingBarangay);
            return ResponseEntity.status(409)
                    .body("Farmer already exists in " + existingBarangay + " !");
        }
        
        System.out.println("No duplicate found. Proceeding to save...");
        // ===== END OF DUPLICATE CHECK =====

        String columns = "farmer_name, contact, address, age, sex, " +
                "cattle_male, cattle_female, carabao_male, carabao_female, " +
                "horse_male, horse_female, goat_male, goat_female, " +
                "sheep_male, sheep_female, swine_male, swine_female, " +
                "chicken_male, chicken_female, duck_male, duck_female, " +
                "quail_male, quail_female, turkey_male, turkey_female, " +
                "geese_male, geese_female, dove_male, dove_female, " +
                "ostrich_male, ostrich_female, guinea_fowl_male, guinea_fowl_female, " +
                "game_fowl_male, game_fowl_female, rabbit_male, rabbit_female, " +
                "dog_male, dog_female, cat_male, cat_female, barangay";

        String[] cols = columns.split("\\s*,\\s*");
        int columnCount = cols.length;
        String placeholders = String.join(",", Collections.nCopies(columnCount, "?"));

        String sql = "INSERT INTO " + table + " (" + columns + ") VALUES (" + placeholders + ")";

        try {
            List<Object> params = new ArrayList<>();
            params.add(livestock.getFarmerName());
            params.add(livestock.getContact());
            params.add(livestock.getAddress());
            params.add(livestock.getAge());
            params.add(livestock.getSex());
            params.add(livestock.getCattle_male());
            params.add(livestock.getCattle_female());
            params.add(livestock.getCarabao_male());
            params.add(livestock.getCarabao_female());
            params.add(livestock.getHorse_male());
            params.add(livestock.getHorse_female());
            params.add(livestock.getGoat_male());
            params.add(livestock.getGoat_female());
            params.add(livestock.getSheep_male());
            params.add(livestock.getSheep_female());
            params.add(livestock.getSwine_male());
            params.add(livestock.getSwine_female());
            params.add(livestock.getChicken_male());
            params.add(livestock.getChicken_female());
            params.add(livestock.getDuck_male());
            params.add(livestock.getDuck_female());
            params.add(livestock.getQuail_male());
            params.add(livestock.getQuail_female());
            params.add(livestock.getTurkey_male());
            params.add(livestock.getTurkey_female());
            params.add(livestock.getGeese_male());
            params.add(livestock.getGeese_female());
            params.add(livestock.getDove_male());
            params.add(livestock.getDove_female());
            params.add(livestock.getOstrich_male());
            params.add(livestock.getOstrich_female());
            params.add(livestock.getGuinea_fowl_male());
            params.add(livestock.getGuinea_fowl_female());
            params.add(livestock.getGame_fowl_male());
            params.add(livestock.getGame_fowl_female());
            params.add(livestock.getRabbit_male());
            params.add(livestock.getRabbit_female());
            params.add(livestock.getDog_male());
            params.add(livestock.getDog_female());
            params.add(livestock.getCat_male());
            params.add(livestock.getCat_female());
            params.add(livestock.getBarangay());

            if (params.size() != columnCount) {
                return ResponseEntity.badRequest()
                        .body("Parameter/column mismatch: columns=" + columnCount + " params=" + params.size());
            }

            jdbcTemplate.update(sql, params.toArray());

            System.out.println("Save successful!");
            return ResponseEntity.ok("Saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Save failed: " + e.getMessage());
            return ResponseEntity.badRequest()
                    .body("Failed to save data: " + e.getMessage());
        }
    }

    @GetMapping("/{barangay}")
    public List<Livestock> getAllLivestock(@PathVariable String barangay) {
        String normalized = normalizeBarangay(barangay);
        String table = table();
        String sql = "SELECT * FROM " + table + " WHERE barangay = ?";

        try {
            return jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(Livestock.class),
                    normalized);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{barangay}/{id}")
    public ResponseEntity<Livestock> getLivestockById(
            @PathVariable String barangay,
            @PathVariable Long id
    ) {
        String normalized = normalizeBarangay(barangay);
        String table = table();
        String sql = "SELECT * FROM " + table + " WHERE id = ? AND barangay = ?";

        try {
            Livestock livestock = jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(Livestock.class),
                    id, normalized
            );
            return ResponseEntity.ok(livestock);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{barangay}/{id}")
    public ResponseEntity<String> updateLivestock(
            @PathVariable String barangay,
            @PathVariable Long id,
            @RequestBody Livestock livestock
    ) {
        String table = table();
        String normalized = normalizeBarangay(barangay);
        livestock.setBarangay(normalized);

        String sql = "UPDATE " + table + " SET " +
                "farmer_name = ?, contact = ?, address = ?, age = ?, sex = ?, " +
                "cattle_male = ?, cattle_female = ?, carabao_male = ?, carabao_female = ?, " +
                "horse_male = ?, horse_female = ?, goat_male = ?, goat_female = ?, " +
                "sheep_male = ?, sheep_female = ?, swine_male = ?, swine_female = ?, " +
                "chicken_male = ?, chicken_female = ?, duck_male = ?, duck_female = ?, " +
                "quail_male = ?, quail_female = ?, turkey_male = ?, turkey_female = ?, " +
                "geese_male = ?, geese_female = ?, dove_male = ?, dove_female = ?, " +
                "ostrich_male = ?, ostrich_female = ?, guinea_fowl_male = ?, guinea_fowl_female = ?, " +
                "game_fowl_male = ?, game_fowl_female = ?, rabbit_male = ?, rabbit_female = ?, " +
                "dog_male = ?, dog_female = ?, cat_male = ?, cat_female = ?, barangay = ? " +
                "WHERE id = ? AND barangay = ?";

        try {
            List<Object> params = new ArrayList<>();
            params.add(livestock.getFarmerName());
            params.add(livestock.getContact());
            params.add(livestock.getAddress());
            params.add(livestock.getAge());
            params.add(livestock.getSex());
            params.add(livestock.getCattle_male());
            params.add(livestock.getCattle_female());
            params.add(livestock.getCarabao_male());
            params.add(livestock.getCarabao_female());
            params.add(livestock.getHorse_male());
            params.add(livestock.getHorse_female());
            params.add(livestock.getGoat_male());
            params.add(livestock.getGoat_female());
            params.add(livestock.getSheep_male());
            params.add(livestock.getSheep_female());
            params.add(livestock.getSwine_male());
            params.add(livestock.getSwine_female());
            params.add(livestock.getChicken_male());
            params.add(livestock.getChicken_female());
            params.add(livestock.getDuck_male());
            params.add(livestock.getDuck_female());
            params.add(livestock.getQuail_male());
            params.add(livestock.getQuail_female());
            params.add(livestock.getTurkey_male());
            params.add(livestock.getTurkey_female());
            params.add(livestock.getGeese_male());
            params.add(livestock.getGeese_female());
            params.add(livestock.getDove_male());
            params.add(livestock.getDove_female());
            params.add(livestock.getOstrich_male());
            params.add(livestock.getOstrich_female());
            params.add(livestock.getGuinea_fowl_male());
            params.add(livestock.getGuinea_fowl_female());
            params.add(livestock.getGame_fowl_male());
            params.add(livestock.getGame_fowl_female());
            params.add(livestock.getRabbit_male());
            params.add(livestock.getRabbit_female());
            params.add(livestock.getDog_male());
            params.add(livestock.getDog_female());
            params.add(livestock.getCat_male());
            params.add(livestock.getCat_female());
            params.add(livestock.getBarangay());
            params.add(id);
            params.add(normalized);

            int rows = jdbcTemplate.update(sql, params.toArray());

            if (rows > 0) {
                return ResponseEntity.ok("Updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("Failed to update data: " + e.getMessage());
        }
    }

    @DeleteMapping("/{barangay}/{id}")
    public ResponseEntity<String> deleteLivestock(
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
}