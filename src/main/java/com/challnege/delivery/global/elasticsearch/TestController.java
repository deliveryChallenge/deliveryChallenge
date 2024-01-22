package com.challnege.delivery.global.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final ESService esService;

    @GetMapping("/test")
    public Iterable<RestaurantDocument> searchRestaurants() {
        return esService.searchAll();
    }

    @GetMapping("/test1")
    public List<RestaurantDocument> searchRestaurants(
            @RequestParam(required = false) String restaurantName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String category) {
        return esService.findBySearchOption(restaurantName, address, category);
    }

    @GetMapping("test2")
    public List<RestaurantDocument> findByCategory(@RequestParam(required = false) String category) {
        return esService.findByCategory(category);
    }
}