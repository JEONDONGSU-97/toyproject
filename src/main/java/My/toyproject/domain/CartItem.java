package My.toyproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "cart_item")
@Getter @Setter
@Slf4j
public class CartItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String name;
    private int count;
    private int price;
    private String size;
    private int totalPrice;

    /**
     *  비즈니스 로직
     */
    //장바구니 상품 생성
    public static CartItem createCartItem(Item item, int price, int count, String size) {
        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setName(item.getName());
        cartItem.setPrice(price);
        cartItem.setCount(count);
        cartItem.setSize(size);

        return cartItem;
    }

    //장바구니 아이템 삭제
    public void removeCartItem() {
        log.info("====================장바구니 아이템 삭제 됨===================");
        log.info("취소된 장바구니 아이템 = {}", item.getName());
    }
}
