package com.challnege.delivery.global.elasticsearch;

import com.challnege.delivery.global.audit.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Getter
@Document(indexName = "delivery")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantDocument {

    @Id
    private long id;
    @Field(name = "restaurant_name", type = FieldType.Text)
    private String restaurantName;
    @Field(type = FieldType.Text)
    private String address;
    @Field(type = FieldType.Text)
    private Category category;
}
