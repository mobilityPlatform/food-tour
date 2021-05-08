package com.gastroventure.lion.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Restaurant extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String imageUrl;

    private String address;

    @Builder
    public Restaurant(String title, String imageUrl){
        this.title = title;
        this.imageUrl = imageUrl;
    }

    @Builder
    public Restaurant(String title, String imageUrl, String address){
        this.title = title;
        this.imageUrl = imageUrl;
        this.address = address;
    }
}
