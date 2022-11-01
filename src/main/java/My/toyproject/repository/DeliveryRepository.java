package My.toyproject.repository;

import My.toyproject.domain.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class DeliveryRepository {

    private final EntityManager em;

    public Delivery findById(Long id) {
        return em.find(Delivery.class, id);
    }
}
