package My.toyproject.domain;

import My.toyproject.repository.CartRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
@Slf4j
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "cart", cascade = ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    //연관관계 메서드(하나의 메서드로 모두 처리)
    public void setMember(Member member) {
        this.member = member;
        member.getCarts().add(this);
    }

    public void setCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }

    /**
     *  비즈니스 로직
     */
    //정적 팩토리 메서드(생성 메서드)
    //장바구니 생성
    public static Cart createCart(Member member, List<CartItem> cartItems) {

        Cart cart = new Cart();
        cart.setMember(member); //연관관계 메서드
        for (CartItem cartItem : cartItems) {
            cart.setCartItem(cartItem); //연관관계 메서드
        }

        return cart;
    }

    //장바구니 비우기
    public void emptyCart() {

        log.info("===================장바구니 비우기========================");

        for (CartItem cartItem : cartItems) {
            cartItem.removeCartItem();
            log.info("====================장바구니 아이템 삭제========================");
        }
    }
}
