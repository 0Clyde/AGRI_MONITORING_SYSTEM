package com.agri.agri_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agri.agri_system.model.Fish;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {

    List<Fish> findAllByBarangay(String barangay);

    Optional<Fish> findByIdAndBarangay(Long id, String barangay);
    
    
    @Query("SELECT f FROM Fish f WHERE f.farmerName = :farmerName AND f.contact = :contact")
    Optional<Fish> findByFarmerNameAndContact(
        @Param("farmerName") String farmerName, 
        @Param("contact") String contact
    );
}