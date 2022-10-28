package My.toyproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "order_item")
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String name;
    private int price;
    private int count;

    /**
     * 비즈니스 로직
     */
    //주문 상품 생성
    public static OrderItem createOrderItem(Item item, int price, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setName(item.getName());
        orderItem.setPrice(price);
        orderItem.setCount(count);

        //주문한 상품 갯수 만큼 재고에서 줄이기
        item.removeStock(count);
        return orderItem;
    }

    //주문 상품 취소
    public void cancel() {
        getItem().addStock(count);
    }

    //주문 상품 가격 조회
    public int totalPrice() {
        return getPrice() * getCount();
    }
}
