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
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ESService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final RestaurantSearchRepositoryImpl restaurantSearchRepositoryImpl;
    private final RestaurantSearchRepository restaurantSearchRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;


    public List<RestaurantDocument> findBySearchOption(String restaurantName, String address, String category) {
        return restaurantSearchRepository.findBySearchOptionList(restaurantName, address, category);
    }
    public Page<RestaurantDocument> searchQuery(String restaurantName,
                                                   String address,
                                                   String category,
                                                   Pageable pageable) {
        Pageable pageRequest = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
        Page<RestaurantDocument> restaurantDocumentPage = restaurantSearchRepository.findByRestaurantNameOrAddressOrCategoryCustom(restaurantName, address, category, pageRequest);
        return restaurantDocumentPage;
    }

    //메인 검색 메소드
    public Page<RestaurantDocument> searchCriteria(String restaurantName,
                                                   String address,
                                                   String category,
                                                   Pageable pageable) {
        Pageable pageRequest = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
        Page<RestaurantDocument> restaurantDocumentPage = restaurantSearchRepositoryImpl.searchByCriteria(restaurantName, address, category, pageRequest);
        return restaurantDocumentPage;
    }

    public List<RestaurantDocument> searchMethodQuery(String restaurantName,
                                         String address,
                                         String category,
                                         Pageable pageable) {
        return restaurantSearchRepository.findByRestaurantNameOrAddressOrCategory(restaurantName, address, category, pageable);
    }

    public List<ResDocResponseDto> findByCategory(String category) {
        return restaurantSearchRepository.findByCategory(category)
                .stream()
                .map(ResDocResponseDto::new)
                .collect(Collectors.toList());
    }

}