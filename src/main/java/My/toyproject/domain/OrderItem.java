package My.toyproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "order_item")
@Getter @Setter
@Slf4j
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
    private String size;

    /**
     * 비즈니스 로직
     */
    //주문 상품 생성
    public static OrderItem createOrderItem(Item item, int price, int count, String size) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setName(item.getName());
        orderItem.setPrice(price);
        orderItem.setCount(count);
        orderItem.setSize(size);

        //주문한 상품 갯수 만큼 재고에서 줄이기
        item.removeStock(count);

        //주문 상품 가격

        log.info("아이템 수량 = {}", item.getStockQuantity());
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
