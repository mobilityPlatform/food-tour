package com.gastroventure.lion.services.resturant;

import com.gastroventure.lion.persistence.entity.Restaurant;
import com.gastroventure.lion.persistence.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RestaurantGetRandomServiceFromAllEntities implements RestaurantGetRandomService{
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> getRandomList(int totalCountOfList) {
        List<Restaurant> all = restaurantRepository.findAll();
        if (all.size() <= totalCountOfList) {
            return restaurantRepository.findAll();
        }

        Random rng = new Random();
        Set<Restaurant> restaurantSet = new HashSet<>();
        while (restaurantSet.size()!=totalCountOfList){
            int next = rng.nextInt(all.size())+1;
            restaurantSet.add(all.get(next));
        }

        return new ArrayList<>(restaurantSet);
    }
}
