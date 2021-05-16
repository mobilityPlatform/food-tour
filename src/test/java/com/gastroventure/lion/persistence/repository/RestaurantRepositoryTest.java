package com.gastroventure.lion.persistence.repository;

import com.gastroventure.lion.persistence.entity.Restaurant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
class RestaurantRepositoryTest {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    @DisplayName("조회 테스트 + 저장테스트")
    void getTest(){

        Restaurant restaurant = restaurantRepository.save(Restaurant.builder().title("집앞 음식점").imageUrl("https://~").build());

        Restaurant one = restaurantRepository.getOne(1L);
        assertEquals(restaurant.getAddress(),one.getAddress());
        assertEquals(restaurant.getImageUrl(),one.getImageUrl());
    }



}