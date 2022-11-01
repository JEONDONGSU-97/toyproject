package My.toyproject.domain;

import My.toyproject.exception.OutOfStockException;
import My.toyproject.exception.OverStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
@Slf4j
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

//    상품에서 주문 상품과 장바구니 상품을 굳이 조회할 필요가 없기 때문에 주석 처리함
//    @OneToMany(mappedBy = "item")
//    private List<CartItem> cartItems = new ArrayList<>();
//
//    @OneToMany(mappedBy = "item")
//    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "item_image_id")
    private ItemImage image;


    private String name;
    private int price;
    private int stockQuantity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    //연관관계 메서드
    public void addCategory(Category category) {
        this.setCategory(category);
        category.getItems().add(this);
    }

    public void addItemImage(ItemImage itemImage) {
        this.setImage(itemImage);
        itemImage.setItem(this);
    }

    /**
     * 비즈니스 로직
     */

    //재고 채우기 (관리자만 가능)
    public void addStock(int quantity) {
        log.info("======================재고 원상복구===========================");
        this.stockQuantity += quantity;
        log.info("기존 수량 + 취소된 상품 수량 = {}, 주문 취소된 상품 수량 = {}", this.stockQuantity, quantity);
        if (stockQuantity > 500) {
            throw new OverStockException("Over stock");
        }
    }

    //재고 비우기
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new OutOfStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
