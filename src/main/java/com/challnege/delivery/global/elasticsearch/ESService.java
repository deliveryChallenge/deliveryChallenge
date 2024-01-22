package com.challnege.delivery.global.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ESService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final RestaurantSearchRepositoryImpl restaurantSearchRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;

//    public Iterable<RestaurantDocument> searchAll() {
//        return restaurantSearchRepository.findAll();
//    }

//    public List<RestaurantDocument> findBySearchOption(String restaurantName, String address, String category) {
//        return restaurantSearchRepository.findBySearchOption(restaurantName, address, category);
//    }

//    public Page<RestaurantDocument> findBySearchOption(Pageable pageable, String restaurantName, String address, String category) {
//        Pageable pageRequest = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
//        return restaurantSearchRepository.findBySearchOption(restaurantName, address, category, pageable);
//    }

//    public Page<RestaurantDocument> findBySearchOption(
//            String restaurantName, String address, String category) {
//        SearchHits<RestaurantDocument> searchHits = restaurantSearchRepository.findBySearchOptionWithDynamicPageable(restaurantName, address, category);
//        Page<RestaurantDocument> documentPage = (Page<RestaurantDocument>) searchHits.map(SearchHit::getContent).stream();
//        return documentPage;
//    }
//
//    public List<RestaurantDocument> findByCategory(String category) {
//        return restaurantSearchRepository.findByCategory(category);
//    }

    public Page<RestaurantDocument> searchCriteria(String restaurantName,
                                                   String address,
                                                   String category,
                                                   Pageable pageable) {
        Pageable pageRequest = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
        Page<RestaurantDocument> restaurantDocumentPage = restaurantSearchRepository.searchByCriteria(restaurantName, address, category, pageRequest);
        return restaurantDocumentPage;
    }
}