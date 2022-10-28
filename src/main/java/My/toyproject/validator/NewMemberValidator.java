package My.toyproject.validator;

import My.toyproject.dto.MemberDto;
import My.toyproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class NewMemberValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberDto.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {

//        UserDto userDto = (UserDto) obj;

        System.out.println("비밀번호 = " + ((MemberDto) obj).getPassword());
        System.out.println("비밀번호 확인 = " + ((MemberDto) obj).getPasswordChk());

        //비밀번호와 비밀번호 확인 매치
        if (!((MemberDto) obj).getPassword().equals(((MemberDto) obj).getPasswordChk())) {
            errors.rejectValue("password", "key", "비밀번호가 일치하지 않습니다.");
        }

        //중복 아이디 확인
        if (!memberRepository.duplicateId(((MemberDto) obj).getLoginId()).isEmpty()) {
            System.out.println("userRepository.findByLoginId(((UserDto) obj).getLoginId()) = " + memberRepository.duplicateId(((MemberDto) obj).getLoginId()));
            errors.rejectValue("loginId", "key", "아이디가 존재합니다.");
        }
    }
}
