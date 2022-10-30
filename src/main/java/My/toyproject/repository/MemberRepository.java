package My.toyproject.repository;

import My.toyproject.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class MemberRepository {
//    스프링 데이터 JPA 사용하면 EntityManager 자동으로 주입해준다.
//    @PersistenceContext
    private final EntityManager em; //final 꼭 붙여주자 안그러면 NullPointerException

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public Member findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    //중복 회원 검증
    public List<Member> duplicateId(String id) {
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", id)
                .getResultList();
    }

    //로그인 아이디로 회원 조회
    public Member findByLoginId(String id) {
        return em.createQuery("select m from Member m where m.loginId = :id", Member.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    //로그인시 회원 이름 추출
    public String MemberFindName(String id) {
        return em.createQuery("select m.name from Member m where m.loginId = :id", String.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
