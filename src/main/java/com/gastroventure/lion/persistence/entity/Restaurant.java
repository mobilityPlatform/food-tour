package com.gastroventure.lion.persistence.entity;

import javax.persistence.*;

@Entity
public class Restaurant extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "restaurant_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String imageUrl;

    private String address;

}
