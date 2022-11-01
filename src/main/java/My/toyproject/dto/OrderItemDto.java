package My.toyproject.dto;

import My.toyproject.domain.Item;
import My.toyproject.domain.status.DeliveryStatus;
import My.toyproject.domain.status.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class OrderItemDto {

    //주문에 관한 정보
    private Long orderId;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private int totalPrice;

    //주문상품에 관한 정보
    private String name;
    private int price;
    private String url;
    private int count;
    private String size;
    private DeliveryStatus deliveryStatus;

    @Builder
    public OrderItemDto(Long orderId, LocalDateTime orderDate, OrderStatus orderStatus, int totalPrice, String name, int price, int count, String size, String url, DeliveryStatus deliveryStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.name = name;
        this.price = price;
        this.count = count;
        this.size = size;
        this.url = url;
        this.deliveryStatus = deliveryStatus;
    }


}
