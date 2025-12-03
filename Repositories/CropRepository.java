package com.agri.agri_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agri.agri_system.model.Crop;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
    
    // Find all crops by barangay
    List<Crop> findByBarangay(String barangay);
    
    // Find crop by ID and barangay - ADD THIS METHOD
    Optional<Crop> findByIdAndBarangay(Long id, String barangay);
    
    // Find by farmer name and contact (for duplicate checking across ALL barangays)
    @Query("SELECT c FROM Crop c WHERE LOWER(TRIM(c.farmer_name)) = LOWER(TRIM(:farmerName)) AND TRIM(c.contact) = TRIM(:contact)")
    Optional<Crop> findByFarmerNameAndContact(
        @Param("farmerName") String farmerName, 
        @Param("contact") String contact
    );
    
    // Find by farmer name (optional - for searching by name only)
    @Query("SELECT c FROM Crop c WHERE LOWER(TRIM(c.farmer_name)) = LOWER(TRIM(:farmerName))")
    List<Crop> findByFarmerName(@Param("farmerName") String farmerName);
    
    // Find by contact (optional - for searching by contact only)
    List<Crop> findByContact(String contact);
}