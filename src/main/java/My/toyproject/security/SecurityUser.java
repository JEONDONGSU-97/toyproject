package My.toyproject.security;

import My.toyproject.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Slf4j
@Getter @Setter
public class SecurityUser extends User {

    private Member member;

    public SecurityUser(Member member) {
        super(member.getLoginId(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));

//        super(member.getLoginId(), "1111", AuthorityUtils.createAuthorityList(member.getRole().toString()));

        log.info("SecurityUser member.loginId = {}", member.getLoginId());
        log.info("SecurityUser member.password = {}", member.getPassword());
        log.info("SecurityUser member.role = {}", member.getRole().toString());

        this.member = member;
    }

}
