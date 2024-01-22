package com.challnege.delivery.global.elasticsearch;

import com.challnege.delivery.global.page.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final ESService esService;
    private final RestaurantSearchRepository restaurantSearchRepository;

    @GetMapping("/testES2")
    public ResponseEntity searchRestaurants(
            @RequestParam(required = false) String restaurantName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String category,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC, page = 1)Pageable pageable) {
        Page<RestaurantDocument> restaurantDocumentPage = esService.searchCriteria(restaurantName, address, category, pageable);
        List<RestaurantDocument> restaurantDocumentList = restaurantDocumentPage.getContent();

        PageDto pageDto = new PageDto<>(ResDocResponseDto.fromDocsListEntity(restaurantDocumentList), restaurantDocumentPage);
        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }
}