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

    //should = or 관계로 처리. 필드값을 원하는 값만 넣어줄 수 있지만 필드마다 or 조건이 적용되서 각 필드에 맞는 값들을 모두 가져옴
    @Query("{\"bool\": {\"should\": [{\"match\": {\"restaurant_name\": \"?0\"}},{\"match\": {\"address\": \"?1\"}},{\"match\": {\"category\": \"?2\"}}]}}")
    Page<RestaurantDocument> findByRestaurantNameOrAddressOrCategoryCustom(String restaurantName, String address, String category, Pageable pageable);

    //match = and 관계로 처리. 모든 필드값이 들어와야하고 and조건에 부합하는 값들을 반환
    @Query("{\"bool\": {\"match\": [{\"match\": {\"restaurant_name\": \"?0\"}},{\"match\": {\"address\": \"?1\"}},{\"match\": {\"category\": \"?2\"}}]}}")
    List<RestaurantDocument> findBySearchOptionList(String restaurantName, String address, String category);

    //JPA 메소드쿼리처럼 형식에 맞게 메소드명을 작성하면 알아서 쿼리가 나감, 페이징 처리도 직접 할 필요 없이 pageable을 넣어주면 알아서 해줌
    List<RestaurantDocument> findByRestaurantNameOrAddressOrCategory(String restaurantName, String address, String category, Pageable pageable);
    List<RestaurantDocument> findByCategory(String category);
}
