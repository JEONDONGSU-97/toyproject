package My.toyproject.repository;

import My.toyproject.domain.Category;
import My.toyproject.domain.Item;
import My.toyproject.domain.ItemImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        em.persist(item);
    }

    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    public Item findByName(String name) {
        return em.createQuery("select i from Item i where i.name = :name", Item.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public List<Item> findCategory() {
        return em.createQuery(
                "select i from Item i" +
                        " join fetch i.category c", Item.class)
                .getResultList();
    }

    //카테고리 부모아이디로 아이템 조회
    public List<Item> findCategoryByParentId(Long id) {
        return em.createQuery(
                "select i from Item i" +
                        " join fetch i.category c" +
                        " where c.id = :id", Item.class)
                .setParameter("id", id)
                .getResultList();
    }

    //카테고리 아이디로 아이템들 조회
    public List<Item> findCategoryItemByName(String name) {
        return em.createQuery(
                "select i from Item i" +
                        " join fetch i.category c" +
                        " where c.name = :name", Item.class)
                .setParameter("name", name)
                .getResultList();
    }

    //카테고리 parent_id로 조회
    public List<Category> findCategoryById(Long id) {
        return em.createQuery(
                "select c from Category c" +
                        " where c.parent.id = :id", Category.class)
                .setParameter("id", id)
                .getResultList();
    }
}
