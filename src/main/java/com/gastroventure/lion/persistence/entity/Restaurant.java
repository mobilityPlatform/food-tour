package com.gastroventure.lion.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "RESTAURANTS")
public class Restaurant {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String imageUrl;

    private String address;

}
