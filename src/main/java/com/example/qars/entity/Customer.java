package com.example.qars.entity;



import javax.persistence.*;
import java.sql.Blob;
import java.sql.SQLException;


@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    //entity names
    @Column(name = "givenName")
    private String givenName;
    @Column(name = "familyName")
    private String familyName;
    @Column(name = "phoneNumber")
    private int phoneNumber;
    @Column(name = "emailaddress")
    private String emailaddress;
    @Column(name = "driverslicense_status")
    private String driverslicense_status;
    @Column(name = "licensephoto")
    @Lob
    private Blob licensephoto;


    public Customer() {
        this.driverslicense_status = "NIET_INGEDIEND";
    }

    public Customer(String givenName, String familyName, int phoneNumber, String emailaddress) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.phoneNumber = phoneNumber;
        this.emailaddress = emailaddress;
        this.driverslicense_status = "NIET_INGEDIEND";
    }

    /** Relations **/
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDriverslicense_status() {
        return this.driverslicense_status;
    }

    public void setDriverslicense_status(String driverslicense_status) {
        this.driverslicense_status = driverslicense_status;
    }

    public String getLicensephoto() throws SQLException {
        byte[] bdata = this.licensephoto.getBytes(1, (int) this.licensephoto.length());
        return new String(bdata);
    }

    public void setLicensephoto(Blob licensephoto) {
        this.licensephoto = licensephoto;
    }
}