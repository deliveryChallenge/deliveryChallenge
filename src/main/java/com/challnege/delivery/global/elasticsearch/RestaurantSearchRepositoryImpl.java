package com.challnege.delivery.global.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class RestaurantSearchRepositoryImpl{

    private final ElasticsearchOperations elasticsearchOperations;

    public Page<RestaurantDocument> searchByCriteria(String restaurantName, String address, String category, Pageable pageable) {
        Criteria criteria = new Criteria();

        if (!restaurantName.isEmpty()) {
            criteria.and("restaurant_name").contains(restaurantName);
        }
        if (!address.isEmpty()) {
            criteria.and("address").contains(address);
        }
        if (!category.isEmpty()) {
            criteria.and("category").contains(category);
        }

        Query query = new CriteriaQuery(criteria).setPageable(pageable);
        SearchHits<RestaurantDocument> search = elasticsearchOperations.search(query, RestaurantDocument.class);

        List<RestaurantDocument> content = new ArrayList<>();
        for (SearchHit<RestaurantDocument> hit : search) {
            content.add(hit.getContent());
        }

        return new PageImpl<>(content, pageable, search.getTotalHits());
    }
}
