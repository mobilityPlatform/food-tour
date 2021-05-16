package com.gastroventure.lion.services.resturant;

import com.gastroventure.lion.persistence.dto.response.ListDto;
import com.gastroventure.lion.persistence.entity.Restaurant;

import java.util.List;

public interface RestaurantGetRandomService {
    List<Restaurant> getRandomList(int totalCountOfList);
}
