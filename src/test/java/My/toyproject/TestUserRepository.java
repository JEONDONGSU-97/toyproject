package My.toyproject;

import My.toyproject.domain.Member;
import My.toyproject.domain.role.Role;
import My.toyproject.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class TestUserRepository {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberRepository userRepository;

//    @Test
//    public void 사용자_등록() {
//        Member user = Member.builder()
//                .loginId("user")
//                .password("1234")
//                .name("회원")
//                .role(Role.ROLE_USER)
//                .build();
////        userRepository.save(user);
//        memberRepository.save(user);
//
//        Member admin = Member.builder()
//                .loginId("admin")
//                .password("1234")
//                .name("어드민")
//                .role(Role.ROLE_ADMIN)
//                .build();
////        userRepository.save(admin);
//        memberRepository.save(admin);
//    }
}
