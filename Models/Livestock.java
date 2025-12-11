package com.agri.agri_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "livestock")
public class Livestock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "farmer_name")
    private String farmerName;

    @Column(name = "contact")
    private String contact;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "cattle_male")
    private int cattle_male;

    @Column(name = "cattle_female")
    private int cattle_female;

    @Column(name = "carabao_male")
    private int carabao_male;

    @Column(name = "carabao_female")
    private int carabao_female;

    @Column(name = "horse_male")
    private int horse_male;

    @Column(name = "horse_female")
    private int horse_female;

    @Column(name = "goat_male")
    private int goat_male;

    @Column(name = "goat_female")
    private int goat_female;

    @Column(name = "sheep_male")
    private int sheep_male;

    @Column(name = "sheep_female")
    private int sheep_female;

    @Column(name = "swine_male")
    private int swine_male;

    @Column(name = "swine_female")
    private int swine_female;

    @Column(name = "chicken_male")
    private int chicken_male;

    @Column(name = "chicken_female")
    private int chicken_female;

    @Column(name = "duck_male")
    private int duck_male;

    @Column(name = "duck_female")
    private int duck_female;

    @Column(name = "quail_male")
    private int quail_male;

    @Column(name = "quail_female")
    private int quail_female;

    @Column(name = "turkey_male")
    private int turkey_male;

    @Column(name = "turkey_female")
    private int turkey_female;

    @Column(name = "geese_male")
    private int geese_male;

    @Column(name = "geese_female")
    private int geese_female;

    @Column(name = "dove_male")
    private int dove_male;

    @Column(name = "dove_female")
    private int dove_female;

    @Column(name = "ostrich_male")
    private int ostrich_male;

    @Column(name = "ostrich_female")
    private int ostrich_female;

    @Column(name = "guinea_fowl_male")
    private int guinea_fowl_male;

    @Column(name = "guinea_fowl_female")
    private int guinea_fowl_female;

    @Column(name = "game_fowl_male")
    private int game_fowl_male;

    @Column(name = "game_fowl_female")
    private int game_fowl_female;

    @Column(name = "rabbit_male")
    private int rabbit_male;

    @Column(name = "rabbit_female")
    private int rabbit_female;

    @Column(name = "dog_male")
    private int dog_male;

    @Column(name = "dog_female")
    private int dog_female;

    @Column(name = "cat_male")
    private int cat_male;

    @Column(name = "cat_female")
    private int cat_female;

    @Column(name = "barangay")
    private String barangay;

    // ===== Getters and Setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
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

    public int getCattle_male() {
        return cattle_male;
    }

    public void setCattle_male(int cattle_male) {
        this.cattle_male = cattle_male;
    }

    public int getCattle_female() {
        return cattle_female;
    }

    public void setCattle_female(int cattle_female) {
        this.cattle_female = cattle_female;
    }

    public int getCarabao_male() {
        return carabao_male;
    }

    public void setCarabao_male(int carabao_male) {
        this.carabao_male = carabao_male;
    }

    public int getCarabao_female() {
        return carabao_female;
    }

    public void setCarabao_female(int carabao_female) {
        this.carabao_female = carabao_female;
    }

    public int getHorse_male() {
        return horse_male;
    }

    public void setHorse_male(int horse_male) {
        this.horse_male = horse_male;
    }

    public int getHorse_female() {
        return horse_female;
    }

    public void setHorse_female(int horse_female) {
        this.horse_female = horse_female;
    }

    public int getGoat_male() {
        return goat_male;
    }

    public void setGoat_male(int goat_male) {
        this.goat_male = goat_male;
    }

    public int getGoat_female() {
        return goat_female;
    }

    public void setGoat_female(int goat_female) {
        this.goat_female = goat_female;
    }

    public int getSheep_male() {
        return sheep_male;
    }

    public void setSheep_male(int sheep_male) {
        this.sheep_male = sheep_male;
    }

    public int getSheep_female() {
        return sheep_female;
    }

    public void setSheep_female(int sheep_female) {
        this.sheep_female = sheep_female;
    }

    public int getSwine_male() {
        return swine_male;
    }

    public void setSwine_male(int swine_male) {
        this.swine_male = swine_male;
    }

    public int getSwine_female() {
        return swine_female;
    }

    public void setSwine_female(int swine_female) {
        this.swine_female = swine_female;
    }

    public int getChicken_male() {
        return chicken_male;
    }

    public void setChicken_male(int chicken_male) {
        this.chicken_male = chicken_male;
    }

    public int getChicken_female() {
        return chicken_female;
    }

    public void setChicken_female(int chicken_female) {
        this.chicken_female = chicken_female;
    }

    public int getDuck_male() {
        return duck_male;
    }

    public void setDuck_male(int duck_male) {
        this.duck_male = duck_male;
    }

    public int getDuck_female() {
        return duck_female;
    }

    public void setDuck_female(int duck_female) {
        this.duck_female = duck_female;
    }

    public int getQuail_male() {
        return quail_male;
    }

    public void setQuail_male(int quail_male) {
        this.quail_male = quail_male;
    }

    public int getQuail_female() {
        return quail_female;
    }

    public void setQuail_female(int quail_female) {
        this.quail_female = quail_female;
    }

    public int getTurkey_male() {
        return turkey_male;
    }

    public void setTurkey_male(int turkey_male) {
        this.turkey_male = turkey_male;
    }

    public int getTurkey_female() {
        return turkey_female;
    }

    public void setTurkey_female(int turkey_female) {
        this.turkey_female = turkey_female;
    }

    public int getGeese_male() {
        return geese_male;
    }

    public void setGeese_male(int geese_male) {
        this.geese_male = geese_male;
    }

    public int getGeese_female() {
        return geese_female;
    }

    public void setGeese_female(int geese_female) {
        this.geese_female = geese_female;
    }

    public int getDove_male() {
        return dove_male;
    }

    public void setDove_male(int dove_male) {
        this.dove_male = dove_male;
    }

    public int getDove_female() {
        return dove_female;
    }

    public void setDove_female(int dove_female) {
        this.dove_female = dove_female;
    }

    public int getOstrich_male() {
        return ostrich_male;
    }

    public void setOstrich_male(int ostrich_male) {
        this.ostrich_male = ostrich_male;
    }

    public int getOstrich_female() {
        return ostrich_female;
    }

    public void setOstrich_female(int ostrich_female) {
        this.ostrich_female = ostrich_female;
    }

    public int getGuinea_fowl_male() {
        return guinea_fowl_male;
    }

    public void setGuinea_fowl_male(int guinea_fowl_male) {
        this.guinea_fowl_male = guinea_fowl_male;
    }

    public int getGuinea_fowl_female() {
        return guinea_fowl_female;
    }

    public void setGuinea_fowl_female(int guinea_fowl_female) {
        this.guinea_fowl_female = guinea_fowl_female;
    }

    public int getGame_fowl_male() {
        return game_fowl_male;
    }

    public void setGame_fowl_male(int game_fowl_male) {
        this.game_fowl_male = game_fowl_male;
    }

    public int getGame_fowl_female() {
        return game_fowl_female;
    }

    public void setGame_fowl_female(int game_fowl_female) {
        this.game_fowl_female = game_fowl_female;
    }

    public int getRabbit_male() {
        return rabbit_male;
    }

    public void setRabbit_male(int rabbit_male) {
        this.rabbit_male = rabbit_male;
    }

    public int getRabbit_female() {
        return rabbit_female;
    }

    public void setRabbit_female(int rabbit_female) {
        this.rabbit_female = rabbit_female;
    }

    public int getDog_male() {
        return dog_male;
    }

    public void setDog_male(int dog_male) {
        this.dog_male = dog_male;
    }

    public int getDog_female() {
        return dog_female;
    }

    public void setDog_female(int dog_female) {
        this.dog_female = dog_female;
    }

    public int getCat_male() {
        return cat_male;
    }

    public void setCat_male(int cat_male) {
        this.cat_male = cat_male;
    }

    public int getCat_female() {
        return cat_female;
    }

    public void setCat_female(int cat_female) {
        this.cat_female = cat_female;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
   }
}
