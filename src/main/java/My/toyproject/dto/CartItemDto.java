package My.toyproject.dto;

import My.toyproject.domain.status.DeliveryStatus;
import My.toyproject.domain.status.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CartItemDto {

    private Long cartId;
    private int totalPrice;

    private String name;
    private int price;
    private String url;
    private int count;
    private String size;

    @Builder
    public CartItemDto(Long cartId, int totalPrice, String name, int price, int count, String size, String url) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.name = name;
        this.price = price;
        this.count = count;
        this.size = size;
        this.url = url;
    }
}
