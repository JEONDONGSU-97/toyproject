package My.toyproject.dto;

import My.toyproject.annotation.ValidEnum;
import My.toyproject.domain.Member;
import My.toyproject.domain.role.Role;
import com.sun.javadoc.MemberDoc;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.*;

@Getter @Setter
public class MemberDto {

    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;

    @NotBlank(message = "아이디를 입력해 주세요.")
    private String loginId;

//    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
//            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자~20자 이어야 합니다.")
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해 주세요.")
    private String passwordChk;

    @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$",
            message = "휴대폰 번호 형식이 올바르지 않습니다. (010-xxxx-xxxx or 010xxxxxxxx)")
    private String mobile;

    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "우편번호를 입력해 주세요.")
    private String zipCode;

    @NotBlank(message = "도로명 주소를 입력해 주세요.")
    private String street;

    @NotBlank(message = "상세주소를 입력해 주세요.")
    private String detail;

    @ValidEnum(enumClass = Role.class)
    private Role role;

    @Builder
    public MemberDto(String name, String email, String mobile, String zipCode, String street, String detail) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.zipCode = zipCode;
        this.street = street;
        this.detail = detail;
    }

}
