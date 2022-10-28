package My.toyproject.controller;

import My.toyproject.domain.Address;
import My.toyproject.domain.role.Role;
import My.toyproject.dto.LoginDto;
import My.toyproject.repository.MemberRepository;
import My.toyproject.security.UserDetailsServiceImpl;
import My.toyproject.validator.NewMemberValidator;
import My.toyproject.domain.Member;
import My.toyproject.dto.MemberDto;
import My.toyproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final UserDetailsServiceImpl userDetailsService;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final NewMemberValidator memberValidator;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signUp")
    public String signUp(MemberDto memberDto) {
        return "signUpForm";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid MemberDto memberDto, BindingResult result) {

        memberValidator.validate(memberDto, result);

        if (result.hasErrors()) {
            log.info("===============" + result.getAllErrors() + "===============");
            log.info("=============== 회원가입 오류 발생 ===============");
            return "signUpForm";
        }

        Address address = new Address(memberDto.getZipCode(), memberDto.getStreet(), memberDto.getDetail());

        Member member = Member.builder()
        .name(memberDto.getName())
        .loginId(memberDto.getLoginId())
        .password(passwordEncoder.encode(memberDto.getPassword()))
        .mobile(memberDto.getMobile())
        .email(memberDto.getEmail())
        .address(address)
        .role(memberDto.getRole())
        .build();

        memberRepository.save(member);

        return "redirect:/";
    }

    //@PostMapping("/login")은 내가 구현하는 것이 아니라 스프링 시큐리티 것을 쓰는것이다.(조심)
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        model.addAttribute("loginDto", new LoginDto());

        log.info("=========================로그인 컨트롤러 수행됨===========================");
        return "loginForm";
    }

    @ModelAttribute("roles")
    public Map<String, Role> roles() {
        Map<String, Role> roles = new ConcurrentHashMap<>();
        roles.put("관리자", Role.ADMIN);
        roles.put("회원", Role.USER);
        return roles;
    }
}
