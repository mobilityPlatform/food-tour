package com.gastroventure.lion.services.resturant;

import com.gastroventure.lion.persistence.entity.Restaurant;
import com.gastroventure.lion.persistence.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantGetRandomServiceFromPage implements RestaurantGetRandomService{
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> getRandomList(int totalCountOfList) {
        return null;
    }
}
