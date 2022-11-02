package My.toyproject.controller;

import My.toyproject.domain.*;
import My.toyproject.domain.status.DeliveryStatus;
import My.toyproject.dto.ItemDto;
import My.toyproject.dto.MemberDto;
import My.toyproject.dto.OrderItemDto;
import My.toyproject.repository.ItemRepository;
import My.toyproject.repository.MemberRepository;
import My.toyproject.repository.OrderRepository;
import My.toyproject.serviceImpl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
@Slf4j
@Transactional
public class PayController {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderServiceImpl orderService;
    private final OrderRepository orderRepository;

    //주문
    @GetMapping("/pay/{name}/{count}/{size}")
    public String pay(@PathVariable String name,
                      @PathVariable int count,
                      @PathVariable String size,
                      Model model) {

        Item item = itemRepository.findByName(name);

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

        //item => OrderItemDto
        OrderItemDto orderItemDto = OrderItemDto.builder()
                .name(item.getName())
                .totalPrice(item.getPrice() * count)
                .price(item.getPrice())
                .count(count)
                .size(size)
                .url(item.getImage().getUrl())
                .build();

        log.info("======================================================");
        log.info("이름 : {}", memberDto.getName());
        log.info("상세 주소 : {}", memberDto.getDetail());
        log.info("도로명 주소 : {}", memberDto.getStreet());
        log.info("우편번호: {}", memberDto.getZipCode());
        log.info("이메일 : {}", memberDto.getEmail());
        log.info("휴대폰번호 : {}", memberDto.getMobile());

        model.addAttribute("orderItemDto", orderItemDto);
        model.addAttribute("member", memberDto);
        return "/shop/payPage";
    }

    //결제 성공
    //주문 상품 생성
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

//        OrderItemDto orderItemDto = new OrderItemDto();
//        orderItemDto.setCount(count);
//        orderItemDto.setSize(size);
        OrderItemDto orderItemDto = OrderItemDto.builder()
                .count(count)
                .size(size)
                .build();

        //주문 로직
        Long orderId = orderService.singleOrder(member.getId(), item.getId(), count, size);
        Order order = orderRepository.findById(orderId);

        model.addAttribute("member", order.getMember());
        model.addAttribute("orderItemDto", orderItemDto);
        model.addAttribute("item", order.getOrderItems().get(0));
        model.addAttribute("delivery", delivery);

        log.info("====================================================================================");
        log.info("count = {}", count);
        log.info("결제 완료!!!!!!!!!!!");
        return "/shop/paySuccess";
    }
}