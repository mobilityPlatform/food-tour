package com.gastroventure.lion.services.resturant;

import com.gastroventure.lion.persistence.entity.Restaurant;
import com.gastroventure.lion.persistence.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        properties = {
               "service.security.enabled=false"
        }
)
@ActiveProfiles("test")
@Transactional
class RestaurantGetRandomServiceFromAllEntitiesTest {

    @Autowired
    private RestaurantGetRandomService restaurantGetRandomServiceFromAllEntities;  // 인터페이스를 구현한 구현체가 여러개일 경우 구현체의 이름으로 구분이 가능
                                                                           // 이것이 편하긴한데, Map으로 가져오는것도 가능해서 무엇이 더 좋은것인지는 모르겠음.
    @Autowired
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp(){
        List<Restaurant> list = new ArrayList<>();
        IntStream.range(0,100).forEach(i->list.add(Restaurant.builder().title(i+" title").imageUrl(i+" imageUrl").address(i+" address").build()));
        restaurantRepository.saveAll(list);
    }
    @Test
    @DisplayName("랜덤리스트 생성 테스트")
    void getRandomList(){
        List<Restaurant> randomList = restaurantGetRandomServiceFromAllEntities.getRandomList(10);
        System.out.println(randomList);
    }
}