package My.toyproject.serviceImpl;

import My.toyproject.domain.Address;
import My.toyproject.domain.Member;
import My.toyproject.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원 가입")
    @Rollback(value = false)
    public void join() throws Exception {
        //given
        Member user = Member.builder()
                .name("박자바")
                .mobile("010-xxxx-xxxx")
                .address(new Address("1234", "서울 용산구 독서당로 6", "xx아파트 xx동 xx호"))
                .build();

        //when
        Long joinId = memberService.join(user);

        //then
        assertEquals(user, memberService.findById(joinId));
    }
}