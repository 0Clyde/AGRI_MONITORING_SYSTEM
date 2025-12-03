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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agri.agri_system.model.Crop;
import com.agri.agri_system.repository.CropRepository;

@RestController
@RequestMapping("/api/crops")
@CrossOrigin(origins = "*")
public class CropController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CropRepository cropRepository;

    private String normalizeBarangay(String barangay) {
        if (barangay == null) return "";
        return barangay.trim().toLowerCase().replace(" ", "_");
    }

    private String table() {
        return "farmers_info.crops";
    }

    // ===== HELPER METHOD: AUTO-CALCULATE TOTAL AREA PLANTED =====
    private void calculateTotalArea(Crop crop) {
        double totalArea = 0.0;
        
        if (crop.getCorn_area() != null) totalArea += crop.getCorn_area();
        if (crop.getTomato_area() != null) totalArea += crop.getTomato_area();
        if (crop.getEggplant_area() != null) totalArea += crop.getEggplant_area();
        if (crop.getBellpepper_area() != null) totalArea += crop.getBellpepper_area();
        if (crop.getSquash_area() != null) totalArea += crop.getSquash_area();
        if (crop.getOkra_area() != null) totalArea += crop.getOkra_area();
        if (crop.getCucumber_area() != null) totalArea += crop.getCucumber_area();
        if (crop.getStringbeans_area() != null) totalArea += crop.getStringbeans_area();
        if (crop.getSweet_potatoes_area() != null) totalArea += crop.getSweet_potatoes_area();
        if (crop.getPotato_area() != null) totalArea += crop.getPotato_area();
        if (crop.getSpinach_area() != null) totalArea += crop.getSpinach_area();
        if (crop.getKangkong_area() != null) totalArea += crop.getKangkong_area();
        if (crop.getCarrots_area() != null) totalArea += crop.getCarrots_area();
        if (crop.getAmpalaya_area() != null) totalArea += crop.getAmpalaya_area();
        if (crop.getMalunggay_area() != null) totalArea += crop.getMalunggay_area();
        if (crop.getCassava_area() != null) totalArea += crop.getCassava_area();
        if (crop.getCabbage_area() != null) totalArea += crop.getCabbage_area();
        if (crop.getLettuce_area() != null) totalArea += crop.getLettuce_area();
        if (crop.getBaguio_beans_area() != null) totalArea += crop.getBaguio_beans_area();
        if (crop.getCauliflower_area() != null) totalArea += crop.getCauliflower_area();
        if (crop.getOnion_area() != null) totalArea += crop.getOnion_area();
        if (crop.getSpring_onion_area() != null) totalArea += crop.getSpring_onion_area();
        if (crop.getGarlic_area() != null) totalArea += crop.getGarlic_area();
        if (crop.getGinger_area() != null) totalArea += crop.getGinger_area();
        
        crop.setTotal_area_planted(totalArea);
    }

    // ===== HELPER METHOD: AUTO-GENERATE FERTILIZER SUMMARY =====
    private void generateFertilizerSummary(Crop crop) {
        List<String> fertilizers = new ArrayList<>();
        
        if (crop.getCorn_fertilizer() != null && !crop.getCorn_fertilizer().trim().isEmpty()) {
            fertilizers.add("Corn: " + crop.getCorn_fertilizer());
        }
        if (crop.getTomato_fertilizer() != null && !crop.getTomato_fertilizer().trim().isEmpty()) {
            fertilizers.add("Tomato: " + crop.getTomato_fertilizer());
        }
        if (crop.getEggplant_fertilizer() != null && !crop.getEggplant_fertilizer().trim().isEmpty()) {
            fertilizers.add("Eggplant: " + crop.getEggplant_fertilizer());
        }
        if (crop.getBellpepper_fertilizer() != null && !crop.getBellpepper_fertilizer().trim().isEmpty()) {
            fertilizers.add("Bellpepper: " + crop.getBellpepper_fertilizer());
        }
        if (crop.getSquash_fertilizer() != null && !crop.getSquash_fertilizer().trim().isEmpty()) {
            fertilizers.add("Squash: " + crop.getSquash_fertilizer());
        }
        if (crop.getOkra_fertilizer() != null && !crop.getOkra_fertilizer().trim().isEmpty()) {
            fertilizers.add("Okra: " + crop.getOkra_fertilizer());
        }
        if (crop.getCucumber_fertilizer() != null && !crop.getCucumber_fertilizer().trim().isEmpty()) {
            fertilizers.add("Cucumber: " + crop.getCucumber_fertilizer());
        }
        if (crop.getStringbeans_fertilizer() != null && !crop.getStringbeans_fertilizer().trim().isEmpty()) {
            fertilizers.add("Stringbeans: " + crop.getStringbeans_fertilizer());
        }
        if (crop.getSweet_potatoes_fertilizer() != null && !crop.getSweet_potatoes_fertilizer().trim().isEmpty()) {
            fertilizers.add("Sweet Potatoes: " + crop.getSweet_potatoes_fertilizer());
        }
        if (crop.getPotato_fertilizer() != null && !crop.getPotato_fertilizer().trim().isEmpty()) {
            fertilizers.add("Potato: " + crop.getPotato_fertilizer());
        }
        if (crop.getSpinach_fertilizer() != null && !crop.getSpinach_fertilizer().trim().isEmpty()) {
            fertilizers.add("Spinach: " + crop.getSpinach_fertilizer());
        }
        if (crop.getKangkong_fertilizer() != null && !crop.getKangkong_fertilizer().trim().isEmpty()) {
            fertilizers.add("Kangkong: " + crop.getKangkong_fertilizer());
        }
        if (crop.getCarrots_fertilizer() != null && !crop.getCarrots_fertilizer().trim().isEmpty()) {
            fertilizers.add("Carrots: " + crop.getCarrots_fertilizer());
        }
        if (crop.getAmpalaya_fertilizer() != null && !crop.getAmpalaya_fertilizer().trim().isEmpty()) {
            fertilizers.add("Ampalaya: " + crop.getAmpalaya_fertilizer());
        }
        if (crop.getMalunggay_fertilizer() != null && !crop.getMalunggay_fertilizer().trim().isEmpty()) {
            fertilizers.add("Malunggay: " + crop.getMalunggay_fertilizer());
        }
        if (crop.getCassava_fertilizer() != null && !crop.getCassava_fertilizer().trim().isEmpty()) {
            fertilizers.add("Cassava: " + crop.getCassava_fertilizer());
        }
        if (crop.getCabbage_fertilizer() != null && !crop.getCabbage_fertilizer().trim().isEmpty()) {
            fertilizers.add("Cabbage: " + crop.getCabbage_fertilizer());
        }
        if (crop.getLettuce_fertilizer() != null && !crop.getLettuce_fertilizer().trim().isEmpty()) {
            fertilizers.add("Lettuce: " + crop.getLettuce_fertilizer());
        }
        if (crop.getBaguio_beans_fertilizer() != null && !crop.getBaguio_beans_fertilizer().trim().isEmpty()) {
            fertilizers.add("Baguio Beans: " + crop.getBaguio_beans_fertilizer());
        }
        if (crop.getCauliflower_fertilizer() != null && !crop.getCauliflower_fertilizer().trim().isEmpty()) {
            fertilizers.add("Cauliflower: " + crop.getCauliflower_fertilizer());
        }
        if (crop.getOnion_fertilizer() != null && !crop.getOnion_fertilizer().trim().isEmpty()) {
            fertilizers.add("Onion: " + crop.getOnion_fertilizer());
        }
        if (crop.getSpring_onion_fertilizer() != null && !crop.getSpring_onion_fertilizer().trim().isEmpty()) {
            fertilizers.add("Spring Onion: " + crop.getSpring_onion_fertilizer());
        }
        if (crop.getGarlic_fertilizer() != null && !crop.getGarlic_fertilizer().trim().isEmpty()) {
            fertilizers.add("Garlic: " + crop.getGarlic_fertilizer());
        }
        if (crop.getGinger_fertilizer() != null && !crop.getGinger_fertilizer().trim().isEmpty()) {
            fertilizers.add("Ginger: " + crop.getGinger_fertilizer());
        }
        
        // Join all fertilizers with semicolon
        if (!fertilizers.isEmpty()) {
            crop.setFertilizer_used(String.join("; ", fertilizers));
        } else {
            crop.setFertilizer_used("None");
        }
    }

    // ===== NEW ENDPOINT: CHECK DUPLICATE FARMER =====
    @GetMapping("/check-duplicate")
    public ResponseEntity<Map<String, Object>> checkDuplicate(
            @RequestParam String farmerName,
            @RequestParam String contact
    ) {
        Optional<Crop> existingFarmer = cropRepository.findByFarmerNameAndContact(farmerName, contact);
        
        Map<String, Object> response = new HashMap<>();
        
        if (existingFarmer.isPresent()) {
            response.put("exists", true);
            response.put("barangay", existingFarmer.get().getBarangay());
        } else {
            response.put("exists", false);
        }
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{barangay}")
    public ResponseEntity<String> saveCrop(
            @PathVariable String barangay,
            @RequestBody Crop crop
    ) {
        String table = table();
        
        String normalizedBarangay = normalizeBarangay(barangay);
        crop.setBarangay(normalizedBarangay);

        // ===== GLOBAL DUPLICATE CHECKING (ACROSS ALL BARANGAYS) =====
        Optional<Crop> existingFarmer = cropRepository.findByFarmerNameAndContact(
            crop.getFarmer_name(), 
            crop.getContact()
        );
        
        if (existingFarmer.isPresent()) {
            String existingBarangay = existingFarmer.get().getBarangay();
            return ResponseEntity.status(409)
                    .body("Farmer already exists in " + existingBarangay + " !");
        }
        // ===== END OF DUPLICATE CHECK =====

        // ===== AUTO-CALCULATE TOTAL AREA =====
        calculateTotalArea(crop);
        
        // ===== AUTO-GENERATE FERTILIZER SUMMARY =====
        generateFertilizerSummary(crop);

        String columns =
                "farmer_name, contact, age, sex, " +
                "corn_area, corn_harvest, corn_fertilizer, " +
                "tomato_area, tomato_harvest, tomato_fertilizer, " +
                "eggplant_area, eggplant_harvest, eggplant_fertilizer, " +
                "bellpepper_area, bellpepper_harvest, bellpepper_fertilizer, " +
                "squash_area, squash_harvest, squash_fertilizer, " +
                "okra_area, okra_harvest, okra_fertilizer, " +
                "cucumber_area, cucumber_harvest, cucumber_fertilizer, " +
                "stringbeans_area, stringbeans_harvest, stringbeans_fertilizer, " +
                "sweet_potatoes_area, sweet_potatoes_harvest, sweet_potatoes_fertilizer, " +
                "potato_area, potato_harvest, potato_fertilizer, " +
                "spinach_area, spinach_harvest, spinach_fertilizer, " +
                "kangkong_area, kangkong_harvest, kangkong_fertilizer, " +
                "carrots_area, carrots_harvest, carrots_fertilizer, " +
                "ampalaya_area, ampalaya_harvest, ampalaya_fertilizer, " +
                "malunggay_area, malunggay_harvest, malunggay_fertilizer, " +
                "cassava_area, cassava_harvest, cassava_fertilizer, " +
                "cabbage_area, cabbage_harvest, cabbage_fertilizer, " +
                "lettuce_area, lettuce_harvest, lettuce_fertilizer, " +
                "baguio_beans_area, baguio_beans_harvest, baguio_beans_fertilizer, " +
                "cauliflower_area, cauliflower_harvest, cauliflower_fertilizer, " +
                "onion_area, onion_harvest, onion_fertilizer, " +
                "spring_onion_area, spring_onion_harvest, spring_onion_fertilizer, " +
                "garlic_area, garlic_harvest, garlic_fertilizer, " +
                "ginger_area, ginger_harvest, ginger_fertilizer, " +
                "total_area_planted, pesticide_used, fertilizer_used, barangay";

        int columnCount = 80;
        String placeholders = String.join(",", Collections.nCopies(columnCount, "?"));

        String sql = "INSERT INTO " + table + " (" + columns + ") VALUES (" + placeholders + ")";

        try {
            jdbcTemplate.update(sql,
                    crop.getFarmer_name(), crop.getContact(), crop.getAge(), crop.getSex(),
                    crop.getCorn_area(), crop.getCorn_harvest(), crop.getCorn_fertilizer(),
                    crop.getTomato_area(), crop.getTomato_harvest(), crop.getTomato_fertilizer(),
                    crop.getEggplant_area(), crop.getEggplant_harvest(), crop.getEggplant_fertilizer(),
                    crop.getBellpepper_area(), crop.getBellpepper_harvest(), crop.getBellpepper_fertilizer(),
                    crop.getSquash_area(), crop.getSquash_harvest(), crop.getSquash_fertilizer(),
                    crop.getOkra_area(), crop.getOkra_harvest(), crop.getOkra_fertilizer(),
                    crop.getCucumber_area(), crop.getCucumber_harvest(), crop.getCucumber_fertilizer(),
                    crop.getStringbeans_area(), crop.getStringbeans_harvest(), crop.getStringbeans_fertilizer(),
                    crop.getSweet_potatoes_area(), crop.getSweet_potatoes_harvest(), crop.getSweet_potatoes_fertilizer(),
                    crop.getPotato_area(), crop.getPotato_harvest(), crop.getPotato_fertilizer(),
                    crop.getSpinach_area(), crop.getSpinach_harvest(), crop.getSpinach_fertilizer(),
                    crop.getKangkong_area(), crop.getKangkong_harvest(), crop.getKangkong_fertilizer(),
                    crop.getCarrots_area(), crop.getCarrots_harvest(), crop.getCarrots_fertilizer(),
                    crop.getAmpalaya_area(), crop.getAmpalaya_harvest(), crop.getAmpalaya_fertilizer(),
                    crop.getMalunggay_area(), crop.getMalunggay_harvest(), crop.getMalunggay_fertilizer(),
                    crop.getCassava_area(), crop.getCassava_harvest(), crop.getCassava_fertilizer(),
                    crop.getCabbage_area(), crop.getCabbage_harvest(), crop.getCabbage_fertilizer(),
                    crop.getLettuce_area(), crop.getLettuce_harvest(), crop.getLettuce_fertilizer(),
                    crop.getBaguio_beans_area(), crop.getBaguio_beans_harvest(), crop.getBaguio_beans_fertilizer(),
                    crop.getCauliflower_area(), crop.getCauliflower_harvest(), crop.getCauliflower_fertilizer(),
                    crop.getOnion_area(), crop.getOnion_harvest(), crop.getOnion_fertilizer(),
                    crop.getSpring_onion_area(), crop.getSpring_onion_harvest(), crop.getSpring_onion_fertilizer(),
                    crop.getGarlic_area(), crop.getGarlic_harvest(), crop.getGarlic_fertilizer(),
                    crop.getGinger_area(), crop.getGinger_harvest(), crop.getGinger_fertilizer(),
                    crop.getTotal_area_planted(), crop.getPesticide_used(), crop.getFertilizer_used(),
                    crop.getBarangay()
            );

            return ResponseEntity.ok("Saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("Failed to save data: " + e.getMessage());
        }
    }

    @GetMapping("/{barangay}")
    public List<Crop> getAllCrops(@PathVariable String barangay) {
        String table = table();
        String normalized = normalizeBarangay(barangay);
        String sql = "SELECT * FROM " + table + " WHERE barangay = ?";

        try {
            return jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(Crop.class),
                    normalized);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{barangay}/{farmerId}")
    public ResponseEntity<Crop> getFarmerById(
            @PathVariable String barangay,
            @PathVariable Long farmerId
    ) {
        String table = table();
        String normalized = normalizeBarangay(barangay);
        String sql = "SELECT * FROM " + table + " WHERE id = ? AND barangay = ?";

        try {
            Crop crop = jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(Crop.class),
                    farmerId,
                    normalized
            );
            return ResponseEntity.ok(crop);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{barangay}/{farmerId}")
    public ResponseEntity<String> updateCrop(
            @PathVariable String barangay,
            @PathVariable Long farmerId,
            @RequestBody Crop crop
    ) {
        String table = table();
        String normalized = normalizeBarangay(barangay);
        crop.setBarangay(normalized);

        // ===== AUTO-CALCULATE TOTAL AREA =====
        calculateTotalArea(crop);
        
        // ===== AUTO-GENERATE FERTILIZER SUMMARY =====
        generateFertilizerSummary(crop);

        String sql = "UPDATE " + table + " SET " +
                "farmer_name = ?, contact = ?, age = ?, sex = ?, " +
                "corn_area = ?, corn_harvest = ?, corn_fertilizer = ?, " +
                "tomato_area = ?, tomato_harvest = ?, tomato_fertilizer = ?, " +
                "eggplant_area = ?, eggplant_harvest = ?, eggplant_fertilizer = ?, " +
                "bellpepper_area = ?, bellpepper_harvest = ?, bellpepper_fertilizer = ?, " +
                "squash_area = ?, squash_harvest = ?, squash_fertilizer = ?, " +
                "okra_area = ?, okra_harvest = ?, okra_fertilizer = ?, " +
                "cucumber_area = ?, cucumber_harvest = ?, cucumber_fertilizer = ?, " +
                "stringbeans_area = ?, stringbeans_harvest = ?, stringbeans_fertilizer = ?, " +
                "sweet_potatoes_area = ?, sweet_potatoes_harvest = ?, sweet_potatoes_fertilizer = ?, " +
                "potato_area = ?, potato_harvest = ?, potato_fertilizer = ?, " +
                "spinach_area = ?, spinach_harvest = ?, spinach_fertilizer = ?, " +
                "kangkong_area = ?, kangkong_harvest = ?, kangkong_fertilizer = ?, " +
                "carrots_area = ?, carrots_harvest = ?, carrots_fertilizer = ?, " +
                "ampalaya_area = ?, ampalaya_harvest = ?, ampalaya_fertilizer = ?, " +
                "malunggay_area = ?, malunggay_harvest = ?, malunggay_fertilizer = ?, " +
                "cassava_area = ?, cassava_harvest = ?, cassava_fertilizer = ?, " +
                "cabbage_area = ?, cabbage_harvest = ?, cabbage_fertilizer = ?, " +
                "lettuce_area = ?, lettuce_harvest = ?, lettuce_fertilizer = ?, " +
                "baguio_beans_area = ?, baguio_beans_harvest = ?, baguio_beans_fertilizer = ?, " +
                "cauliflower_area = ?, cauliflower_harvest = ?, cauliflower_fertilizer = ?, " +
                "onion_area = ?, onion_harvest = ?, onion_fertilizer = ?, " +
                "spring_onion_area = ?, spring_onion_harvest = ?, spring_onion_fertilizer = ?, " +
                "garlic_area = ?, garlic_harvest = ?, garlic_fertilizer = ?, " +
                "ginger_area = ?, ginger_harvest = ?, ginger_fertilizer = ?, " +
                "total_area_planted = ?, pesticide_used = ?, fertilizer_used = ?, " +
                "barangay = ? " +
                "WHERE id = ? AND barangay = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    crop.getFarmer_name(), crop.getContact(), crop.getAge(), crop.getSex(),
                    crop.getCorn_area(), crop.getCorn_harvest(), crop.getCorn_fertilizer(),
                    crop.getTomato_area(), crop.getTomato_harvest(), crop.getTomato_fertilizer(),
                    crop.getEggplant_area(), crop.getEggplant_harvest(), crop.getEggplant_fertilizer(),
                    crop.getBellpepper_area(), crop.getBellpepper_harvest(), crop.getBellpepper_fertilizer(),
                    crop.getSquash_area(), crop.getSquash_harvest(), crop.getSquash_fertilizer(),
                    crop.getOkra_area(), crop.getOkra_harvest(), crop.getOkra_fertilizer(),
                    crop.getCucumber_area(), crop.getCucumber_harvest(), crop.getCucumber_fertilizer(),
                    crop.getStringbeans_area(), crop.getStringbeans_harvest(), crop.getStringbeans_fertilizer(),
                    crop.getSweet_potatoes_area(), crop.getSweet_potatoes_harvest(), crop.getSweet_potatoes_fertilizer(),
                    crop.getPotato_area(), crop.getPotato_harvest(), crop.getPotato_fertilizer(),
                    crop.getSpinach_area(), crop.getSpinach_harvest(), crop.getSpinach_fertilizer(),
                    crop.getKangkong_area(), crop.getKangkong_harvest(), crop.getKangkong_fertilizer(),
                    crop.getCarrots_area(), crop.getCarrots_harvest(), crop.getCarrots_fertilizer(),
                    crop.getAmpalaya_area(), crop.getAmpalaya_harvest(), crop.getAmpalaya_fertilizer(),
                    crop.getMalunggay_area(), crop.getMalunggay_harvest(), crop.getMalunggay_fertilizer(),
                    crop.getCassava_area(), crop.getCassava_harvest(), crop.getCassava_fertilizer(),
                    crop.getCabbage_area(), crop.getCabbage_harvest(), crop.getCabbage_fertilizer(),
                    crop.getLettuce_area(), crop.getLettuce_harvest(), crop.getLettuce_fertilizer(),
                    crop.getBaguio_beans_area(), crop.getBaguio_beans_harvest(), crop.getBaguio_beans_fertilizer(),
                    crop.getCauliflower_area(), crop.getCauliflower_harvest(), crop.getCauliflower_fertilizer(),
                    crop.getOnion_area(), crop.getOnion_harvest(), crop.getOnion_fertilizer(),
                    crop.getSpring_onion_area(), crop.getSpring_onion_harvest(), crop.getSpring_onion_fertilizer(),
                    crop.getGarlic_area(), crop.getGarlic_harvest(), crop.getGarlic_fertilizer(),
                    crop.getGinger_area(), crop.getGinger_harvest(), crop.getGinger_fertilizer(),
                    crop.getTotal_area_planted(), crop.getPesticide_used(), crop.getFertilizer_used(),
                    crop.getBarangay(),          
                    farmerId, normalized          
            );

            if (rowsAffected > 0) {
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

    @DeleteMapping("/{barangay}/{farmerId}")
    public ResponseEntity<String> deleteCrop(
            @PathVariable String barangay,
            @PathVariable Long farmerId
    ) {
        String table = table();
        String normalized = normalizeBarangay(barangay);
        String sql = "DELETE FROM " + table + " WHERE id = ? AND barangay = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, farmerId, normalized);
            if (rowsAffected > 0) {
                return ResponseEntity.ok("Deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("Failed to delete data: " + e.getMessage());
        }
    }
}
