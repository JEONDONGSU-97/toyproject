package My.toyproject.repository;

import My.toyproject.domain.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartRepository {

    private final EntityManager em;

    public void save(Cart cart) {
        em.persist(cart);
    }

    public Cart findById(Long id) {
        return em.find(Cart.class, id);
    }

    //회원 고유 아이디로 조회
    public List<Cart> findByMemberId(Long id) {
        return em.createQuery("select c from Cart c where c.member.id = :id", Cart.class)
                .setParameter("id", id)
                .getResultList();
    }

    //장바구니 삭제
    public void deleteCart(Long id) {
        Cart cart = findById(id);
        em.remove(cart);
    }
}
