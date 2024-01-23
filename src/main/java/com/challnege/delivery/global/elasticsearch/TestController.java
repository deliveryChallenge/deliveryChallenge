package com.challnege.delivery.global.elasticsearch;

import com.challnege.delivery.global.page.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final ESService esService;
    private final RestaurantSearchRepository restaurantSearchRepository;


    @GetMapping("/testES0")
    public List<RestaurantDocument> searchRestaurants(
            @RequestParam(required = false) String restaurantName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String category) {
        return esService.findBySearchOption(restaurantName, address, category);
    }

    @GetMapping("/testES1")
    public ResponseEntity searchQuery(
            @RequestParam(required = false) String restaurantName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String category,
            @PageableDefault(size = 20, page = 1) Pageable pageable) {
        Page<RestaurantDocument> restaurantDocumentPage = esService.searchQuery(restaurantName, address, category, pageable);
        List<RestaurantDocument> restaurantDocumentList = restaurantDocumentPage.getContent();

        PageDto pageDto = new PageDto<>(ResDocResponseDto.fromDocsListEntity(restaurantDocumentList), restaurantDocumentPage);
        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    //메인 검색 메소드
    @GetMapping("/testES2")
    public ResponseEntity searchCriteria(
            @RequestParam(required = false) String restaurantName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String category,
            @PageableDefault(size = 20, page = 1) Pageable pageable) {
        Page<RestaurantDocument> restaurantDocumentPage = esService.searchCriteria(restaurantName, address, category, pageable);
        List<RestaurantDocument> restaurantDocumentList = restaurantDocumentPage.getContent();

        PageDto pageDto = new PageDto<>(ResDocResponseDto.fromDocsListEntity(restaurantDocumentList), restaurantDocumentPage);
        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @GetMapping("/testES4")
    public List<RestaurantDocument> searchMethodQuery(
            @RequestParam(required = false) String restaurantName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String category,
            @PageableDefault(size = 20, page = 1) Pageable pageable) {
        return esService.searchMethodQuery(restaurantName, address, category, pageable);
    }

    @GetMapping("/testES5")
    public ResponseEntity<List<ResDocResponseDto>> searchByCategory(@RequestParam String category
                                                                    ) {
        return ResponseEntity.ok(esService.findByCategory(category));
    }
}