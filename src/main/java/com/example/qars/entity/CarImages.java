package com.example.qars.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.sql.Blob;
import java.sql.SQLException;

@Entity
@Table(name = "car_images")
public class CarImages extends BaseEntity {

    @Column(name = "interior")
    @Lob
    private Blob interior;

    @Column(name = "sideview_left")
    @Lob
    private Blob sideview_left;

    @Column(name = "sideview_right")
    @Lob
    private Blob sideview_right;

    @Column(name = "backside")
    @Lob
    private Blob backside;

    @Column(name = "front")
    @Lob
    private Blob front;

    public CarImages() {
    }

    public String getInterior() throws SQLException {
        byte[] bdata = this.interior.getBytes(1, (int) this.interior.length());
        return new String(bdata);
    }

    public void setInterior(Blob interior) {
        this.interior = interior;
    }

    public String getSideview_left() throws SQLException {
        byte[] bdata = this.sideview_left.getBytes(1, (int) this.sideview_left.length());
        return new String(bdata);
    }

    public void setSideview_left(Blob sideview_left) {
        this.sideview_left = sideview_left;
    }

    public String getSideview_right() throws SQLException {
        byte[] bdata = this.sideview_right.getBytes(1, (int) this.sideview_right.length());
        return new String(bdata);
    }

    public void setSideview_right(Blob sideview_right) {
        this.sideview_right = sideview_right;
    }

    public String getBackside() throws SQLException {
        byte[] bdata = this.backside.getBytes(1, (int) this.backside.length());
        return new String(bdata);
    }

    public void setBackside(Blob backside) {
        this.backside = backside;
    }

    public String getFront() throws SQLException {
        byte[] bdata = this.front.getBytes(1, (int) this.front.length());
        return new String(bdata);
    }

    public void setFront(Blob front) {
        this.front = front;
    }
}
