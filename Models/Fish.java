package com.agri.agri_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "fisheries") 
public class Fish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Farmer name is required")
    @Column(name = "farmer_name", nullable = false)
    private String farmerName;

    @NotBlank(message = "Contact is required")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "Contact must be 10-11 digits")
    @Column(name = "contact", nullable = false)
    private String contact;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than 100")
    @Column(name = "age", nullable = false)
    private Integer age;

    @NotBlank(message = "Sex is required")
    @Pattern(regexp = "^(M|F|O)$", message = "Sex must be M, F, or O")
    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "tilapia")
    private int tilapia;

    @Column(name = "bangus")
    private int bangus;

    @Column(name = "hito")
    private int hito;

    @NotBlank(message = "Barangay is required")
    @Column(name = "barangay", nullable = false)
    private String barangay;

    @Column(name = "tilapia_ponds")
    private int tilapiaPonds;

    @Column(name = "tilapia_area")
    private double tilapiaArea;

    @Column(name = "tilapia_stocks")
    private int tilapiaStocks;

    @Column(name = "bangus_ponds")
    private int bangusPonds;

    @Column(name = "bangus_area")
    private double bangusArea;

    @Column(name = "bangus_stocks")
    private int bangusStocks;

    @Column(name = "hito_ponds")
    private int hitoPonds;

    @Column(name = "hito_area")
    private double hitoArea;

    @Column(name = "hito_stocks")
    private int hitoStocks;

    @Column(name = "crawfish_ponds")
    private int crawfishPonds;

    @Column(name = "crawfish_area")
    private double crawfishArea;

    @Column(name = "crawfish_stocks")
    private int crawfishStocks;

    @Column(name = "total_pond_area")
    private double totalPondArea;

    @Column(name = "total_avg_production")
    private double totalAvgProduction;

    @Column(name = "total_feeds_used")
    private int totalFeedsUsed;

    @Column(name = "tilapia_feeds")
    private int tilapiaFeeds;

    @Column(name = "bangus_feeds")
    private int bangusFeeds;

    @Column(name = "catfish_feeds")
    private int catfishFeeds;

    @Column(name = "crawfish_feeds")
    private int crawfishFeeds;

    
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

    public int getTilapia() {
        return tilapia;
    }

    public void setTilapia(int tilapia) {
        this.tilapia = tilapia;
    }

    public int getBangus() {
        return bangus;
    }

    public void setBangus(int bangus) {
        this.bangus = bangus;
    }

    public int getHito() {
        return hito;
    }

    public void setHito(int hito) {
        this.hito = hito;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public int getTilapiaPonds() {
        return tilapiaPonds;
    }

    public void setTilapiaPonds(int tilapiaPonds) {
        this.tilapiaPonds = tilapiaPonds;
    }

    public double getTilapiaArea() {
        return tilapiaArea;
    }

    public void setTilapiaArea(double tilapiaArea) {
        this.tilapiaArea = tilapiaArea;
    }

    public int getTilapiaStocks() {
        return tilapiaStocks;
    }

    public void setTilapiaStocks(int tilapiaStocks) {
        this.tilapiaStocks = tilapiaStocks;
    }

    public int getBangusPonds() {
        return bangusPonds;
    }

    public void setBangusPonds(int bangusPonds) {
        this.bangusPonds = bangusPonds;
    }

    public double getBangusArea() {
        return bangusArea;
    }

    public void setBangusArea(double bangusArea) {
        this.bangusArea = bangusArea;
    }

    public int getBangusStocks() {
        return bangusStocks;
    }

    public void setBangusStocks(int bangusStocks) {
        this.bangusStocks = bangusStocks;
    }

    public int getHitoPonds() {
        return hitoPonds;
    }

    public void setHitoPonds(int hitoPonds) {
        this.hitoPonds = hitoPonds;
    }

    public double getHitoArea() {
        return hitoArea;
    }

    public void setHitoArea(double hitoArea) {
        this.hitoArea = hitoArea;
    }

    public int getHitoStocks() {
        return hitoStocks;
    }

    public void setHitoStocks(int hitoStocks) {
        this.hitoStocks = hitoStocks;
    }

    public int getCrawfishPonds() {
        return crawfishPonds;
    }

    public void setCrawfishPonds(int crawfishPonds) {
        this.crawfishPonds = crawfishPonds;
    }

    public double getCrawfishArea() {
        return crawfishArea;
    }

    public void setCrawfishArea(double crawfishArea) {
        this.crawfishArea = crawfishArea;
    }

    public int getCrawfishStocks() {
        return crawfishStocks;
    }

    public void setCrawfishStocks(int crawfishStocks) {
        this.crawfishStocks = crawfishStocks;
    }

    public double getTotalPondArea() {
        return totalPondArea;
    }

    public void setTotalPondArea(double totalPondArea) {
        this.totalPondArea = totalPondArea;
    }

    public double getTotalAvgProduction() {
        return totalAvgProduction;
    }

    public void setTotalAvgProduction(double totalAvgProduction) {
        this.totalAvgProduction = totalAvgProduction;
    }

    public int getTotalFeedsUsed() {
        return totalFeedsUsed;
    }

    public void setTotalFeedsUsed(int totalFeedsUsed) {
        this.totalFeedsUsed = totalFeedsUsed;
    }

    public int getTilapiaFeeds() {
        return tilapiaFeeds;
    }

    public void setTilapiaFeeds(int tilapiaFeeds) {
        this.tilapiaFeeds = tilapiaFeeds;
    }

    public int getBangusFeeds() {
        return bangusFeeds;
    }

    public void setBangusFeeds(int bangusFeeds) {
        this.bangusFeeds = bangusFeeds;
    }

    public int getCatfishFeeds() {
        return catfishFeeds;
    }

    public void setCatfishFeeds(int catfishFeeds) {
        this.catfishFeeds = catfishFeeds;
    }

    public int getCrawfishFeeds() {
        return crawfishFeeds;
    }

    public void setCrawfishFeeds(int crawfishFeeds) {
        this.crawfishFeeds = crawfishFeeds;
    }
}