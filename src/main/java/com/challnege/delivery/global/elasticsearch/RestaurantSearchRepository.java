package com.challnege.delivery.global.elasticsearch;

import com.challnege.delivery.domain.restaurant.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantSearchRepository extends ElasticsearchRepository<RestaurantDocument, Long> {

    @Query("{\"bool\": {\"should\": [{\"match\": {\"restaurant_name\": \"?0\"}},{\"match\": {\"address\": \"?1\"}},{\"match\": {\"category\": \"?2\"}}]}}")
    List<RestaurantDocument> findBySearchOption(String restaurantName, String address, String category);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"address\": \"?0\"}}]}}")
    List<RestaurantDocument> findByCategory(String category);

//    @Query("{\"from\": 0, \"size\": 20, \"query\": {\"bool\": {\"should\": [{\"match\": {\"restaurant_name\": \"?0\"}},{\"match\": {\"address\": \"?1\"}},{\"match\": {\"category\": \"?2\"}}]}}}")
//    SearchHits<RestaurantDocument> findBySearchOptionWithDynamicPageable(String restaurantName, String address, String category);
}
