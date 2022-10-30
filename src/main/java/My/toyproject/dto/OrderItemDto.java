package My.toyproject.dto;

import My.toyproject.domain.Item;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemDto {

    //상품명과 가격
    private String name;
    private int price;

    //주문상품수량과 사이즈
    private int count;
    private String size;

}
