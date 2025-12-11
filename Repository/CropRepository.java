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
    
    
    List<Crop> findByBarangay(String barangay);
    
    
    Optional<Crop> findByIdAndBarangay(Long id, String barangay);
    
    
    @Query("SELECT c FROM Crop c WHERE LOWER(TRIM(c.farmer_name)) = LOWER(TRIM(:farmerName)) AND TRIM(c.contact) = TRIM(:contact)")
    Optional<Crop> findByFarmerNameAndContact(
        @Param("farmerName") String farmerName, 
        @Param("contact") String contact
    );
    
    
    @Query("SELECT c FROM Crop c WHERE LOWER(TRIM(c.farmer_name)) = LOWER(TRIM(:farmerName))")
    List<Crop> findByFarmerName(@Param("farmerName") String farmerName);
    
    
    List<Crop> findByContact(String contact);
}