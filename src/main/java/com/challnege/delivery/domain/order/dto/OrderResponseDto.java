package com.challnege.delivery.domain.order.dto;

import com.challnege.delivery.domain.order.entity.Order;
import com.challnege.delivery.domain.order.entity.Status;
import com.challnege.delivery.domain.ordermenu.entity.OrderMenu;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderResponseDto {

    private List<OrderMenu> orderMenuList;
    private Long totalPrice;
    private Status status;//enum으로 할 지 고민
    private String restaurantName;
    private String restaurantNumber;


    @Builder
    public OrderResponseDto(List<OrderMenu> orderMenuList, Long totalPrice, Status status, String restaurantName, String restaurantNumber) {
        this.orderMenuList = orderMenuList;
        this.totalPrice = totalPrice;
        this.status = status;
        this.restaurantName = restaurantName;
        this.restaurantNumber = restaurantNumber;
    }

    public static OrderResponseDto fromEntity(Order order) {
        return OrderResponseDto.builder()
                .orderMenuList(order.getOrderMenuList())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .restaurantName(order.getRestaurant().getRestaurantName())
                .restaurantNumber(order.getRestaurant().getResNumber())
                .build();
    }
}