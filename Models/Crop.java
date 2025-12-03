package com.agri.agri_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "crops") 
public class Crop {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "farmer_name")
    private String farmer_name;
    
    @Column(name = "contact")
    private String contact;
    
    @Column(name = "age")
    private Integer age;
    
    @Column(name = "sex")
    private String sex;

   
    @Column(name = "corn_area")
    private Double corn_area;
    
    @Column(name = "corn_harvest")
    private Double corn_harvest;
    
    @Column(name = "corn_fertilizer")
    private String corn_fertilizer;

    
    @Column(name = "tomato_area")
    private Double tomato_area;
    
    @Column(name = "tomato_harvest")
    private Double tomato_harvest;
    
    @Column(name = "tomato_fertilizer")
    private String tomato_fertilizer;

    
    @Column(name = "eggplant_area")
    private Double eggplant_area;
    
    @Column(name = "eggplant_harvest")
    private Double eggplant_harvest;
    
    @Column(name = "eggplant_fertilizer")
    private String eggplant_fertilizer;

    
    @Column(name = "bellpepper_area")
    private Double bellpepper_area;
    
    @Column(name = "bellpepper_harvest")
    private Double bellpepper_harvest;
    
    @Column(name = "bellpepper_fertilizer")
    private String bellpepper_fertilizer;

  
    @Column(name = "squash_area")
    private Double squash_area;
    
    @Column(name = "squash_harvest")
    private Double squash_harvest;
    
    @Column(name = "squash_fertilizer")
    private String squash_fertilizer;

   
    @Column(name = "okra_area")
    private Double okra_area;
    
    @Column(name = "okra_harvest")
    private Double okra_harvest;
    
    @Column(name = "okra_fertilizer")
    private String okra_fertilizer;

    @Column(name = "cucumber_area")
    private Double cucumber_area;
    
    @Column(name = "cucumber_harvest")
    private Double cucumber_harvest;
    
    @Column(name = "cucumber_fertilizer")
    private String cucumber_fertilizer;

    
    @Column(name = "stringbeans_area")
    private Double stringbeans_area;
    
    @Column(name = "stringbeans_harvest")
    private Double stringbeans_harvest;
    
    @Column(name = "stringbeans_fertilizer")
    private String stringbeans_fertilizer;

    
    @Column(name = "sweet_potatoes_area")
    private Double sweet_potatoes_area;
    
    @Column(name = "sweet_potatoes_harvest")
    private Double sweet_potatoes_harvest;
    
    @Column(name = "sweet_potatoes_fertilizer")
    private String sweet_potatoes_fertilizer;

    @Column(name = "potato_area")
    private Double potato_area;
    
    @Column(name = "potato_harvest")
    private Double potato_harvest;
    
    @Column(name = "potato_fertilizer")
    private String potato_fertilizer;

    
    @Column(name = "spinach_area")
    private Double spinach_area;
    
    @Column(name = "spinach_harvest")
    private Double spinach_harvest;
    
    @Column(name = "spinach_fertilizer")
    private String spinach_fertilizer;

   
    @Column(name = "kangkong_area")
    private Double kangkong_area;
    
    @Column(name = "kangkong_harvest")
    private Double kangkong_harvest;
    
    @Column(name = "kangkong_fertilizer")
    private String kangkong_fertilizer;

   
    @Column(name = "carrots_area")
    private Double carrots_area;
    
    @Column(name = "carrots_harvest")
    private Double carrots_harvest;
    
    @Column(name = "carrots_fertilizer")
    private String carrots_fertilizer;

    
    @Column(name = "ampalaya_area")
    private Double ampalaya_area;
    
    @Column(name = "ampalaya_harvest")
    private Double ampalaya_harvest;
    
    @Column(name = "ampalaya_fertilizer")
    private String ampalaya_fertilizer;

    
    @Column(name = "malunggay_area")
    private Double malunggay_area;
    
    @Column(name = "malunggay_harvest")
    private Double malunggay_harvest;
    
    @Column(name = "malunggay_fertilizer")
    private String malunggay_fertilizer;

    
    @Column(name = "cassava_area")
    private Double cassava_area;
    
    @Column(name = "cassava_harvest")
    private Double cassava_harvest;
    
    @Column(name = "cassava_fertilizer")
    private String cassava_fertilizer;

    
    @Column(name = "cabbage_area")
    private Double cabbage_area;
    
    @Column(name = "cabbage_harvest")
    private Double cabbage_harvest;
    
    @Column(name = "cabbage_fertilizer")
    private String cabbage_fertilizer;

   
    @Column(name = "lettuce_area")
    private Double lettuce_area;
    
    @Column(name = "lettuce_harvest")
    private Double lettuce_harvest;
    
    @Column(name = "lettuce_fertilizer")
    private String lettuce_fertilizer;

    
    @Column(name = "baguio_beans_area")
    private Double baguio_beans_area;
    
    @Column(name = "baguio_beans_harvest")
    private Double baguio_beans_harvest;
    
    @Column(name = "baguio_beans_fertilizer")
    private String baguio_beans_fertilizer;

    
    @Column(name = "cauliflower_area")
    private Double cauliflower_area;
    
    @Column(name = "cauliflower_harvest")
    private Double cauliflower_harvest;
    
    @Column(name = "cauliflower_fertilizer")
    private String cauliflower_fertilizer;

   
    @Column(name = "onion_area")
    private Double onion_area;
    
    @Column(name = "onion_harvest")
    private Double onion_harvest;
    
    @Column(name = "onion_fertilizer")
    private String onion_fertilizer;

   
    @Column(name = "spring_onion_area")
    private Double spring_onion_area;
    
    @Column(name = "spring_onion_harvest")
    private Double spring_onion_harvest;
    
    @Column(name = "spring_onion_fertilizer")
    private String spring_onion_fertilizer;

    
    @Column(name = "garlic_area")
    private Double garlic_area;
    
    @Column(name = "garlic_harvest")
    private Double garlic_harvest;
    
    @Column(name = "garlic_fertilizer")
    private String garlic_fertilizer;

    
    @Column(name = "ginger_area")
    private Double ginger_area;
    
    @Column(name = "ginger_harvest")
    private Double ginger_harvest;
    
    @Column(name = "ginger_fertilizer")
    private String ginger_fertilizer;

    @Column(name = "total_area_planted")
    private Double total_area_planted;
    
    @Column(name = "pesticide_used")
    private String pesticide_used;
    
    @Column(name = "fertilizer_used")
    private String fertilizer_used;
    
    @Column(name = "barangay")
    private String barangay;

   

    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getFarmer_name() { 
        return farmer_name; 
    }
    
    public void setFarmer_name(String farmer_name) { 
        this.farmer_name = farmer_name; 
    }

    public String getContact() { 
        return contact; 
    }
    
    public void setContact(String contact) { 
        this.contact = contact; 
    }

    public Integer getAge() { 
        return age; 
    }
    
    public void setAge(Integer age) { 
        this.age = age; 
    }

    public String getSex() { 
        return sex; 
    }
    
    public void setSex(String sex) { 
        this.sex = sex; 
    }

    
    public Double getCorn_area() { 
        return corn_area; 
    }
    
    public void setCorn_area(Double corn_area) { 
        this.corn_area = corn_area; 
    }
    
    public Double getCorn_harvest() { 
        return corn_harvest; 
    }
    
    public void setCorn_harvest(Double corn_harvest) { 
        this.corn_harvest = corn_harvest; 
    }
    
    public String getCorn_fertilizer() { 
        return corn_fertilizer; 
    }
    
    public void setCorn_fertilizer(String corn_fertilizer) { 
        this.corn_fertilizer = corn_fertilizer; 
    }

    public Double getTomato_area() { 
        return tomato_area; 
    }
    
    public void setTomato_area(Double tomato_area) { 
        this.tomato_area = tomato_area; 
    }
    
    public Double getTomato_harvest() { 
        return tomato_harvest; 
    }
    
    public void setTomato_harvest(Double tomato_harvest) { 
        this.tomato_harvest = tomato_harvest; 
    }
    
    public String getTomato_fertilizer() { 
        return tomato_fertilizer; 
    }
    
    public void setTomato_fertilizer(String tomato_fertilizer) { 
        this.tomato_fertilizer = tomato_fertilizer; 
    }

    // === Eggplant ===
    public Double getEggplant_area() { 
        return eggplant_area; 
    }
    
    public void setEggplant_area(Double eggplant_area) { 
        this.eggplant_area = eggplant_area; 
    }
    
    public Double getEggplant_harvest() { 
        return eggplant_harvest; 
    }
    
    public void setEggplant_harvest(Double eggplant_harvest) { 
        this.eggplant_harvest = eggplant_harvest; 
    }
    
    public String getEggplant_fertilizer() { 
        return eggplant_fertilizer; 
    }
    
    public void setEggplant_fertilizer(String eggplant_fertilizer) { 
        this.eggplant_fertilizer = eggplant_fertilizer; 
    }

    
    public Double getBellpepper_area() { 
        return bellpepper_area; 
    }
    
    public void setBellpepper_area(Double bellpepper_area) { 
        this.bellpepper_area = bellpepper_area; 
    }
    
    public Double getBellpepper_harvest() { 
        return bellpepper_harvest; 
    }
    
    public void setBellpepper_harvest(Double bellpepper_harvest) { 
        this.bellpepper_harvest = bellpepper_harvest; 
    }
    
    public String getBellpepper_fertilizer() { 
        return bellpepper_fertilizer; 
    }
    
    public void setBellpepper_fertilizer(String bellpepper_fertilizer) { 
        this.bellpepper_fertilizer = bellpepper_fertilizer; 
    }

    
    public Double getSquash_area() { 
        return squash_area; 
    }
    
    public void setSquash_area(Double squash_area) { 
        this.squash_area = squash_area; 
    }
    
    public Double getSquash_harvest() { 
        return squash_harvest; 
    }
    
    public void setSquash_harvest(Double squash_harvest) { 
        this.squash_harvest = squash_harvest; 
    }
    
    public String getSquash_fertilizer() { 
        return squash_fertilizer; 
    }
    
    public void setSquash_fertilizer(String squash_fertilizer) { 
        this.squash_fertilizer = squash_fertilizer; 
    }

    
    public Double getOkra_area() { 
        return okra_area; 
    }
    
    public void setOkra_area(Double okra_area) { 
        this.okra_area = okra_area; 
    }
    
    public Double getOkra_harvest() { 
        return okra_harvest; 
    }
    
    public void setOkra_harvest(Double okra_harvest) { 
        this.okra_harvest = okra_harvest; 
    }
    
    public String getOkra_fertilizer() { 
        return okra_fertilizer; 
    }
    
    public void setOkra_fertilizer(String okra_fertilizer) { 
        this.okra_fertilizer = okra_fertilizer; 
    }

    
    public Double getCucumber_area() { 
        return cucumber_area; 
    }
    
    public void setCucumber_area(Double cucumber_area) { 
        this.cucumber_area = cucumber_area; 
    }
    
    public Double getCucumber_harvest() { 
        return cucumber_harvest; 
    }
    
    public void setCucumber_harvest(Double cucumber_harvest) { 
        this.cucumber_harvest = cucumber_harvest; 
    }
    
    public String getCucumber_fertilizer() { 
        return cucumber_fertilizer; 
    }
    
    public void setCucumber_fertilizer(String cucumber_fertilizer) { 
        this.cucumber_fertilizer = cucumber_fertilizer; 
    }

    
    public Double getStringbeans_area() { 
        return stringbeans_area; 
    }
    
    public void setStringbeans_area(Double stringbeans_area) { 
        this.stringbeans_area = stringbeans_area; 
    }
    
    public Double getStringbeans_harvest() { 
        return stringbeans_harvest; 
    }
    
    public void setStringbeans_harvest(Double stringbeans_harvest) { 
        this.stringbeans_harvest = stringbeans_harvest; 
    }
    
    public String getStringbeans_fertilizer() { 
        return stringbeans_fertilizer; 
    }
    
    public void setStringbeans_fertilizer(String stringbeans_fertilizer) { 
        this.stringbeans_fertilizer = stringbeans_fertilizer; 
    }

    
    public Double getSweet_potatoes_area() { 
        return sweet_potatoes_area; 
    }
    
    public void setSweet_potatoes_area(Double sweet_potatoes_area) { 
        this.sweet_potatoes_area = sweet_potatoes_area; 
    }
    
    public Double getSweet_potatoes_harvest() { 
        return sweet_potatoes_harvest; 
    }
    
    public void setSweet_potatoes_harvest(Double sweet_potatoes_harvest) { 
        this.sweet_potatoes_harvest = sweet_potatoes_harvest; 
    }
    
    public String getSweet_potatoes_fertilizer() { 
        return sweet_potatoes_fertilizer; 
    }
    
    public void setSweet_potatoes_fertilizer(String sweet_potatoes_fertilizer) { 
        this.sweet_potatoes_fertilizer = sweet_potatoes_fertilizer; 
    }

    
    public Double getPotato_area() { 
        return potato_area; 
    }
    
    public void setPotato_area(Double potato_area) { 
        this.potato_area = potato_area; 
    }
    
    public Double getPotato_harvest() { 
        return potato_harvest; 
    }
    
    public void setPotato_harvest(Double potato_harvest) { 
        this.potato_harvest = potato_harvest; 
    }
    
    public String getPotato_fertilizer() { 
        return potato_fertilizer; 
    }
    
    public void setPotato_fertilizer(String potato_fertilizer) { 
        this.potato_fertilizer = potato_fertilizer; 
    }

    
    public Double getSpinach_area() { 
        return spinach_area; 
    }
    
    public void setSpinach_area(Double spinach_area) { 
        this.spinach_area = spinach_area; 
    }
    
    public Double getSpinach_harvest() { 
        return spinach_harvest; 
    }
    
    public void setSpinach_harvest(Double spinach_harvest) { 
        this.spinach_harvest = spinach_harvest; 
    }
    
    public String getSpinach_fertilizer() { 
        return spinach_fertilizer; 
    }
    
    public void setSpinach_fertilizer(String spinach_fertilizer) { 
        this.spinach_fertilizer = spinach_fertilizer; 
    }

    
    public Double getKangkong_area() { 
        return kangkong_area; 
    }
    
    public void setKangkong_area(Double kangkong_area) { 
        this.kangkong_area = kangkong_area; 
    }
    
    public Double getKangkong_harvest() { 
        return kangkong_harvest; 
    }
    
    public void setKangkong_harvest(Double kangkong_harvest) { 
        this.kangkong_harvest = kangkong_harvest; 
    }
    
    public String getKangkong_fertilizer() { 
        return kangkong_fertilizer; 
    }
    
    public void setKangkong_fertilizer(String kangkong_fertilizer) { 
        this.kangkong_fertilizer = kangkong_fertilizer; 
    }

    
    public Double getCarrots_area() { 
        return carrots_area; 
    }
    
    public void setCarrots_area(Double carrots_area) { 
        this.carrots_area = carrots_area; 
    }
    
    public Double getCarrots_harvest() { 
        return carrots_harvest; 
    }
    
    public void setCarrots_harvest(Double carrots_harvest) { 
        this.carrots_harvest = carrots_harvest; 
    }
    
    public String getCarrots_fertilizer() { 
        return carrots_fertilizer; 
    }
    
    public void setCarrots_fertilizer(String carrots_fertilizer) { 
        this.carrots_fertilizer = carrots_fertilizer; 
    }

    
    public Double getAmpalaya_area() { 
        return ampalaya_area; 
    }
    
    public void setAmpalaya_area(Double ampalaya_area) { 
        this.ampalaya_area = ampalaya_area; 
    }
    
    public Double getAmpalaya_harvest() { 
        return ampalaya_harvest; 
    }
    
    public void setAmpalaya_harvest(Double ampalaya_harvest) { 
        this.ampalaya_harvest = ampalaya_harvest; 
    }
    
    public String getAmpalaya_fertilizer() { 
        return ampalaya_fertilizer; 
    }
    
    public void setAmpalaya_fertilizer(String ampalaya_fertilizer) { 
        this.ampalaya_fertilizer = ampalaya_fertilizer; 
    }

    
    public Double getMalunggay_area() { 
        return malunggay_area; 
    }
    
    public void setMalunggay_area(Double malunggay_area) { 
        this.malunggay_area = malunggay_area; 
    }
    
    public Double getMalunggay_harvest() { 
        return malunggay_harvest; 
    }
    
    public void setMalunggay_harvest(Double malunggay_harvest) { 
        this.malunggay_harvest = malunggay_harvest; 
    }
    
    public String getMalunggay_fertilizer() { 
        return malunggay_fertilizer; 
    }
    
    public void setMalunggay_fertilizer(String malunggay_fertilizer) { 
        this.malunggay_fertilizer = malunggay_fertilizer; 
    }

    
    public Double getCassava_area() { 
        return cassava_area; 
    }
    
    public void setCassava_area(Double cassava_area) { 
        this.cassava_area = cassava_area; 
    }
    
    public Double getCassava_harvest() { 
        return cassava_harvest; 
    }
    
    public void setCassava_harvest(Double cassava_harvest) { 
        this.cassava_harvest = cassava_harvest; 
    }
    
    public String getCassava_fertilizer() { 
        return cassava_fertilizer; 
    }
    
    public void setCassava_fertilizer(String cassava_fertilizer) { 
        this.cassava_fertilizer = cassava_fertilizer; 
    }

    
    public Double getCabbage_area() { 
        return cabbage_area; 
    }
    
    public void setCabbage_area(Double cabbage_area) { 
        this.cabbage_area = cabbage_area; 
    }
    
    public Double getCabbage_harvest() { 
        return cabbage_harvest; 
    }
    
    public void setCabbage_harvest(Double cabbage_harvest) { 
        this.cabbage_harvest = cabbage_harvest; 
    }
    
    public String getCabbage_fertilizer() { 
        return cabbage_fertilizer; 
    }
    
    public void setCabbage_fertilizer(String cabbage_fertilizer) { 
        this.cabbage_fertilizer = cabbage_fertilizer; 
    }

    
    public Double getLettuce_area() { 
        return lettuce_area; 
    }
    
    public void setLettuce_area(Double lettuce_area) { 
        this.lettuce_area = lettuce_area; 
    }
    
    public Double getLettuce_harvest() { 
        return lettuce_harvest; 
    }
    
    public void setLettuce_harvest(Double lettuce_harvest) { 
        this.lettuce_harvest = lettuce_harvest; 
    }
    
    public String getLettuce_fertilizer() { 
        return lettuce_fertilizer; 
    }
    
    public void setLettuce_fertilizer(String lettuce_fertilizer) { 
        this.lettuce_fertilizer = lettuce_fertilizer; 
    }

    
    public Double getBaguio_beans_area() { 
        return baguio_beans_area; 
    }
    
    public void setBaguio_beans_area(Double baguio_beans_area) { 
        this.baguio_beans_area = baguio_beans_area; 
    }
    
    public Double getBaguio_beans_harvest() { 
        return baguio_beans_harvest; 
    }
    
    public void setBaguio_beans_harvest(Double baguio_beans_harvest) { 
        this.baguio_beans_harvest = baguio_beans_harvest; 
    }
    
    public String getBaguio_beans_fertilizer() { 
        return baguio_beans_fertilizer; 
    }
    
    public void setBaguio_beans_fertilizer(String baguio_beans_fertilizer) { 
        this.baguio_beans_fertilizer = baguio_beans_fertilizer; 
    }

    
    public Double getCauliflower_area() { 
        return cauliflower_area; 
    }
    
    public void setCauliflower_area(Double cauliflower_area) { 
        this.cauliflower_area = cauliflower_area; 
    }
    
    public Double getCauliflower_harvest() { 
        return cauliflower_harvest; 
    }
    
    public void setCauliflower_harvest(Double cauliflower_harvest) { 
        this.cauliflower_harvest = cauliflower_harvest; 
    }
    
    public String getCauliflower_fertilizer() { 
        return cauliflower_fertilizer; 
    }
    
    public void setCauliflower_fertilizer(String cauliflower_fertilizer) { 
        this.cauliflower_fertilizer = cauliflower_fertilizer; 
    }

    
    public Double getOnion_area() { 
        return onion_area; 
    }
    
    public void setOnion_area(Double onion_area) { 
        this.onion_area = onion_area; 
    }
    
    public Double getOnion_harvest() { 
        return onion_harvest; 
    }
    
    public void setOnion_harvest(Double onion_harvest) { 
        this.onion_harvest = onion_harvest; 
    }
    
    public String getOnion_fertilizer() { 
        return onion_fertilizer; 
    }
    
    public void setOnion_fertilizer(String onion_fertilizer) { 
        this.onion_fertilizer = onion_fertilizer; 
    }

    
    public Double getSpring_onion_area() { 
        return spring_onion_area; 
    }
    
    public void setSpring_onion_area(Double spring_onion_area) { 
        this.spring_onion_area = spring_onion_area; 
    }
    
    public Double getSpring_onion_harvest() { 
        return spring_onion_harvest; 
    }
    
    public void setSpring_onion_harvest(Double spring_onion_harvest) { 
        this.spring_onion_harvest = spring_onion_harvest; 
    }
    
    public String getSpring_onion_fertilizer() { 
        return spring_onion_fertilizer; 
    }
    
    public void setSpring_onion_fertilizer(String spring_onion_fertilizer) { 
        this.spring_onion_fertilizer = spring_onion_fertilizer; 
    }

    
    public Double getGarlic_area() { 
        return garlic_area; 
    }
    
    public void setGarlic_area(Double garlic_area) { 
        this.garlic_area = garlic_area; 
    }
    
    public Double getGarlic_harvest() { 
        return garlic_harvest; 
    }
    
    public void setGarlic_harvest(Double garlic_harvest) { 
        this.garlic_harvest = garlic_harvest; 
    }
    
    public String getGarlic_fertilizer() { 
        return garlic_fertilizer; 
    }
    
    public void setGarlic_fertilizer(String garlic_fertilizer) { 
        this.garlic_fertilizer = garlic_fertilizer; 
    }

    
    public Double getGinger_area() { 
        return ginger_area; 
    }
    
    public void setGinger_area(Double ginger_area) { 
        this.ginger_area = ginger_area; 
    }
    
    public Double getGinger_harvest() { 
        return ginger_harvest; 
    }
    
    public void setGinger_harvest(Double ginger_harvest) { 
        this.ginger_harvest = ginger_harvest; 
    }
    
    public String getGinger_fertilizer() { 
        return ginger_fertilizer; 
    }
    
    public void setGinger_fertilizer(String ginger_fertilizer) { 
        this.ginger_fertilizer = ginger_fertilizer; 
    }

    
    public Double getTotal_area_planted() { 
        return total_area_planted; 
    }
    
    public void setTotal_area_planted(Double total_area_planted) { 
        this.total_area_planted = total_area_planted; 
    }
    
    public String getPesticide_used() { 
        return pesticide_used; 
    }
    
    public void setPesticide_used(String pesticide_used) { 
        this.pesticide_used = pesticide_used; 
    }
    
    public String getFertilizer_used() { 
        return fertilizer_used; 
    }
    
    public void setFertilizer_used(String fertilizer_used) { 
        this.fertilizer_used = fertilizer_used; 
    }

    public String getBarangay() { 
        return barangay; 
    }
    
    public void setBarangay(String barangay) { 
        this.barangay = barangay; 
    }
}