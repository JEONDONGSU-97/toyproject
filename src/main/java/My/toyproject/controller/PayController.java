package My.toyproject.controller;

import My.toyproject.domain.Delivery;
import My.toyproject.domain.Item;
import My.toyproject.domain.Member;
import My.toyproject.domain.OrderItem;
import My.toyproject.domain.status.DeliveryStatus;
import My.toyproject.dto.ItemDto;
import My.toyproject.dto.MemberDto;
import My.toyproject.dto.OrderItemDto;
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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String pay(@PathVariable String name, OrderItemDto orderItemDto, Model model) {

        Item item = itemRepository.findByName(name);
        ItemDto itemDto = new ItemDto(item);

        //사용자 정보 가져오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        String loginID = ((UserDetails) principal).getUsername();
        String password = ((UserDetails) principal).getPassword();

        Member member = memberRepository.findByLoginId(loginID);
        MemberDto memberDto = MemberDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .mobile(member.getMobile())
                .zipCode(member.getAddress().getZipCode())
                .street(member.getAddress().getStreet())
                .detail(member.getAddress().getDetail())
                .build();

        log.info("======================================================");
        log.info("이름 : {}", memberDto.getName());
        log.info("상세 주소 : {}", memberDto.getDetail());
        log.info("도로명 주소 : {}", memberDto.getStreet());
        log.info("우편번호: {}", memberDto.getZipCode());
        log.info("이메일 : {}", memberDto.getEmail());
        log.info("휴대폰번호 : {}", memberDto.getMobile());

        model.addAttribute("item", itemDto);
        model.addAttribute("member", memberDto);
        return "/shop/payPage";
    }

//    @GetMapping("/pay/success/{orderNum}/{orderItem}/{orderItemUrl}/{orderItemPrice}")
    @GetMapping("/pay/success/" + '"' + "{memberName}" + '"' + '/' + '"' + "{name}" + '"' + "/{count}" + '/' + '"' + "{size}" + '"')
    public String paySuccess(@PathVariable String memberName, @PathVariable String name, @PathVariable int count, @PathVariable String size, Model model) {

        Item item = itemRepository.findByName(name);
        ItemDto itemDto = new ItemDto(item);

        Member member = memberRepository.findByName(memberName);
        MemberDto memberDto = MemberDto.builder()
                .name(member.getName())
                .street(member.getAddress().getStreet())
                .detail(member.getAddress().getDetail())
                .build();

        Delivery delivery = new Delivery();
        delivery.setStatus(DeliveryStatus.READY);

        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setCount(count);
        orderItemDto.setSize(size);

        model.addAttribute("member", memberDto);
        model.addAttribute("orderItemDto", orderItemDto);
        model.addAttribute("item", itemDto);
        model.addAttribute("delivery", delivery);

        log.info("====================================================================================");
        log.info("count = {}", count);
        log.info("결제 완료!!!!!!!!!!!");
        return "/shop/paySuccess";
    }
}
