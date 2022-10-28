package My.toyproject.repository;

import My.toyproject.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    public void save(Category category) {
        em.persist(category);
    }

    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Category.class)
                .getResultList();
    }

    //부모아이디로 자식카테고리 조회
    public List<Category> findByParentId(Long id) {
        return em.createQuery(
                "select c from Category c" +
                        " join fetch c.parent p" +
                        " where c.parent.id = :id", Category.class)
                .setParameter("id", id)
                .getResultList();
    }
}
