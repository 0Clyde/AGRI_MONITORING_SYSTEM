package com.agri.agri_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agri.agri_system.model.Livestock;

@Repository
public interface LivestockRepository extends JpaRepository<Livestock, Long> {

    List<Livestock> findByBarangayIgnoreCase(String barangay);

    List<Livestock> findByFarmerNameContainingIgnoreCase(String name);
    
    
    @Query("SELECT l FROM Livestock l WHERE LOWER(TRIM(l.farmerName)) = LOWER(TRIM(:farmerName)) AND TRIM(l.contact) = TRIM(:contact)")
    Optional<Livestock> findByFarmerNameAndContact(
        @Param("farmerName") String farmerName, 
        @Param("contact") String contact
    );
}