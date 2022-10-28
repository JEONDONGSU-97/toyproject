package My.toyproject.service;

import My.toyproject.domain.Member;

import java.util.List;

public interface MemberService {

    //회원 가입
    public Long join(Member member);

    //모든 회원 조회
    public List<Member> findAll();

    //회원 단건 조회
    public Member findById(Long id);
}
