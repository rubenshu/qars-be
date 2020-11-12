package com.example.qars.entity;



import javax.persistence.*;



@Entity
@Table(name = "booking")
public class Booking extends BaseEntity {

    @Column(name = "pickUpLocationCode")
    private String pickUpLocationCode;
    @Column(name = "dropOffLocationCode")
    private String dropOffLocationCode;
    @Column(name = "totalAmount")
    private int totalAmount;
    @Column(name = "bookingDate")
    private String bookingDate;
    @Column(name = "beginDate")
    private String beginDate;
    @Column(name = "endDate")
    private String endDate;

    public Booking() {
    }

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    public String getPickUpLocationCode() {
        return pickUpLocationCode;
    }

    public void setPickUpLocationCode(String pickUpLocationCode) {
        this.pickUpLocationCode = pickUpLocationCode;
    }

    public String getDropOffLocationCode() {
        return dropOffLocationCode;
    }

    public void setDropOffLocationCode(String dropOffLocationCode) {
        this.dropOffLocationCode = dropOffLocationCode;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int i) {
        this.totalAmount = i;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }
}
