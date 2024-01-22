package com.challnege.delivery.global.elasticsearch;

import com.challnege.delivery.domain.restaurant.entity.Restaurant;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantSearchRepository extends ElasticsearchRepository<RestaurantDocument, Long> {

    @Query("{\"bool\": {\"should\": [{\"match\": {\"restaurant_name\": \"?0\"}},{\"match\": {\"address\": \"?1\"}},{\"match\": {\"category\": \"?2\"}}]}}")
    List<RestaurantDocument> findBySearchOption(String restaurantName, String address, String category);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"address\": \"?0\"}}]}}")
    List<RestaurantDocument> findByCategory(String category);


}
