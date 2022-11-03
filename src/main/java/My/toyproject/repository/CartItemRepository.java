package My.toyproject.repository;

import My.toyproject.domain.Cart;
import My.toyproject.domain.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartItemRepository {

    private final EntityManager em;

    public void save(CartItem cartItem) {
        em.persist(cartItem);
    }

    public CartItem findById(Long id) {
        return em.find(CartItem.class, id);
    }

    //장바구니 아이디로 해당하는 장바구니 아이템들 리스트로 조회
    public List<CartItem> findByCartId(Long id) {
        return em.createQuery("select ci from CartItem ci where ci.cart.id = :id", CartItem.class)
                .setParameter("id", id)
                .getResultList();
    }

    //장바구니 아이템 모두 조회
    public List<CartItem> findAll() {
        return em.createQuery("select ci from CartItem ci", CartItem.class)
                .getResultList();
    }

    //장바구니 아이템 상품아이디로 조회
    public CartItem findByItemId(Long id) {
        return em.createQuery("select ci from CartItem ci where ci.item.id = :id", CartItem.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
