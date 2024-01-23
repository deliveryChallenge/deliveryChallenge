package com.challnege.delivery.global.elasticsearch;

import com.challnege.delivery.global.audit.Category;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ResDocResponseDto {

    private long id;
    private String restaurantName;
    private String address;
    private Category category;

    @Builder
    public ResDocResponseDto(long id, String restaurantName, String address, Category category) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.address = address;
        this.category = category;
    }

    public ResDocResponseDto(RestaurantDocument restaurantDocument) {
        this.id = restaurantDocument.getId();
        this.restaurantName = restaurantDocument.getRestaurantName();
        this.address = restaurantDocument.getAddress();
        this.category = restaurantDocument.getCategory();
    }

    public static ResDocResponseDto fromDocsEntity(RestaurantDocument restaurantDocument) {
        return ResDocResponseDto.builder()
                .id(restaurantDocument.getId())
                .restaurantName(restaurantDocument.getRestaurantName())
                .address(restaurantDocument.getAddress())
                .category(restaurantDocument.getCategory())
                .build();
    }

    public static List<ResDocResponseDto> fromDocsListEntity(List<RestaurantDocument> restaurantDocumentList) {
        return restaurantDocumentList.stream()
                .map(ResDocResponseDto::fromDocsEntity)
                .collect(Collectors.toList());
    }
}
