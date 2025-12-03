package com.agri.agri_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agri.agri_system.model.Crop;
import com.agri.agri_system.repository.CropRepository;

@Service
public class CropService {

    @Autowired
    private CropRepository cropRepository;

    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    public List<Crop> getCropsByBarangay(String barangay) {
        return cropRepository.findByBarangay(barangay);
    }

    public Crop saveCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    public Optional<Crop> getByIdAndBarangay(Long id, String barangay) {
        return cropRepository.findByIdAndBarangay(id, barangay);
    }

    public void deleteById(Long id) {
        cropRepository.deleteById(id);
    }
}
