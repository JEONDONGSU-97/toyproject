package My.toyproject.controller;

import My.toyproject.domain.Item;
import My.toyproject.domain.Member;
import My.toyproject.dto.ItemDto;
import My.toyproject.repository.ItemRepository;
import My.toyproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
@Slf4j
public class PayController {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/pay/{name}")
    public String pay(ItemDto itemDto, @PathVariable String name, Model model) {

        Item item = itemRepository.findByName(name);
        itemDto = new ItemDto(item);

        //사용자 정보 가져오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        String loginID = ((UserDetails) principal).getUsername();
        String password = ((UserDetails) principal).getPassword();

        List<Member> members = memberRepository.findByLoginId(loginID);
        Member member = members.get(0);

        log.info("======================================================");
        log.info("이름 : {}", member.getName());
        log.info("아이디 : {}", member.getLoginId());
        log.info("비밀번호 : {}", member.getPassword());
        log.info("상세 주소 : {}", member.getAddress().getDetail());
        log.info("도로명 주소 : {}", member.getAddress().getStreet());
        log.info("우편번호: {}", member.getAddress().getZipCode());
        log.info("이메일 : {}", member.getEmail());
        log.info("휴대폰번호 : {}", member.getMobile());

        model.addAttribute("item", itemDto);
        return "/shop/payPage";
    }
}
