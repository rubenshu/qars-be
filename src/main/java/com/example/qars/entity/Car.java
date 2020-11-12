package com.example.qars.entity;


import javax.persistence.*;
import java.sql.Blob;
import java.sql.SQLException;

@Entity
@Table(name = "car")
public class Car extends BaseEntity {

    //entity names
    @Column(name = "carmodel")
    private String carmodel;
    @Column(name = "cardescription")
    private String cardescription;
    @Column(name = "licenseplate")
    private String licenseplate;
    @Column(name = "mileage")
    private int mileage;
    @Column(name = "priceph")
    private double priceph;
    @Column(name = "seats")
    private int seats;
    @Column(name = "horsepower")
    private int horsepower;
    @Column(name = "counter")
    private int counter;
    @Column(name = "available")
    private boolean available;
    @Column(name = "carphoto")
    @Lob
    private Blob carphoto;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "establishment_id", referencedColumnName = "id")
    private Establishment establishment;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_images_id", referencedColumnName = "id")
    private CarImages carImages;


    public Car() {

    }

    public Establishment getEstablishment() {
        return this.establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public String getCardescription() {
        return cardescription;
    }

    public void setCardescription(String cardescription) {
        this.cardescription = cardescription;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getPriceph() {
        return priceph;
    }

    public void setPriceph(double priceph) {
        this.priceph = priceph;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getCarphoto() throws SQLException {
        byte[] bdata = this.carphoto.getBytes(1, (int) this.carphoto.length());
        return new String(bdata);
    }

    public CarImages getCarImages() {
        return carImages;
    }

    public void setCarImages(CarImages carImages) {
        this.carImages = carImages;
    }

    public void setCarphoto(Blob carphoto) {
        this.carphoto = carphoto;
    }
}
