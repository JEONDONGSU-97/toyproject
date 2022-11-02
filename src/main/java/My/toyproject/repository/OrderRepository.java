package My.toyproject.repository;

import My.toyproject.domain.Order;
import My.toyproject.domain.status.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findByDate(LocalDateTime date) {
        return em.createQuery("select o from Order o where o.orderDate = :date", Order.class)
                .setParameter("date", date)
                .getResultList();
    }

    public List<Order> findByStatus(OrderStatus status) {
        return em.createQuery("select o from Order o where o.orderStatus = :status", Order.class)
                .setParameter("status", status)
                .getResultList();
    }

    public List<Order> findByMemberId(Long id) {
        return em.createQuery("select o from Order o where o.member.id = :id", Order.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

}
