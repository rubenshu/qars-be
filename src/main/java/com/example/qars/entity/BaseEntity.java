package com.example.qars.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** Empty constructor for creation **/
    public BaseEntity(){}

    /************ Setters & Getters ************/
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}