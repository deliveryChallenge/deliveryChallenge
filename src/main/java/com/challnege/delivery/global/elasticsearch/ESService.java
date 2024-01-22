package com.challnege.delivery.global.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ESService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final RestaurantSearchRepository restaurantSearchRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;

    public Iterable<RestaurantDocument> searchAll() {
        return restaurantSearchRepository.findAll();
    }

    public List<RestaurantDocument> findBySearchOption(String restaurantName, String address, String category) {
        return restaurantSearchRepository.findBySearchOption(restaurantName, address, category);
    }

    public List<RestaurantDocument> findByCategory(String category) {
        return restaurantSearchRepository.findByCategory(category);
    }
}