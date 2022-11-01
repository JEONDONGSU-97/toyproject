package My.toyproject.controller;

import My.toyproject.domain.*;
import My.toyproject.domain.status.DeliveryStatus;
import My.toyproject.dto.ItemDto;
import My.toyproject.dto.MemberDto;
import My.toyproject.dto.OrderItemDto;
import My.toyproject.repository.*;
import My.toyproject.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
@Transactional
public class HomeController {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    //회원 주문 조회
    private final OrderRepository orderRepository;
    //회원 주문 아이템 조회
    private final OrderItemRepository orderItemRepository;
    //회원 주문 아이템 배송 상태 조회
    private final DeliveryRepository deliveryRepository;

    @GetMapping("/practice")
    public String practice(Model model) {
        List<Item> items = itemRepository.findCategory();

        List<ItemDto> itemDto = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", itemDto);

        for (Item item : items) {
            System.out.println("item = " + item.getName());
            System.out.println("item.getImage().getUrl() = " + item.getImage().getUrl());
        }

        return "practice";
    }

    @GetMapping("/")
    public String homeLoginSession(@AuthenticationPrincipal SecurityUser principal, HttpServletRequest request, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("============================================로그인 후 홈화면=======================================================");
        log.info(authentication.getName(), authentication.getPrincipal(), authentication.getCredentials(), authentication.getDetails());
        log.info("sessionId = {}", RequestContextHolder.currentRequestAttributes().getSessionId());
        log.info("============================================로그인 후 홈화면=======================================================");

        return "home";
    }

    @GetMapping("/forbidden")
    public String forbidden() {
        log.info("forbidden 페이지");
        return "forbidden";
    }

    @GetMapping("/myPage")
    public String myPage() {
        return "myPage";
    }

    @GetMapping("/myCart")
    public String myCart() {
        return "/shop/myCart";
    }

    //주문로직
    @GetMapping("/myOrder/{loginId}")
    public String myOrder(@PathVariable String loginId, Model model) {

        Member member = memberRepository.findByLoginId(loginId);
        Long memberId = member.getId();

        //회원 주문 조회
        List<Order> orderList = orderRepository.findByMemberId(memberId);
        List<Long> orderIdList = new ArrayList<>();

        //회원 주문 아이템 조회
        List<OrderItemDto> orderItemList = new ArrayList<>();
        for (Order order : orderList) {
            OrderItem orderItem = orderItemRepository.findById(order.getId());
            Delivery delivery = deliveryRepository.findById(order.getDelivery().getId());

            OrderItemDto orderItemDto = OrderItemDto.builder()
                    .orderId(order.getId())
                    .orderDate(order.getOrderDate())
                    .orderStatus(order.getOrderStatus())
                    .totalPrice(order.getTotalPrice())
                    .name(orderItem.getName())
                    .price(orderItem.getPrice())
                    .count(orderItem.getCount())
                    .size(orderItem.getSize())
                    .url(orderItem.getItem().getImage().getUrl())
                    .deliveryStatus(delivery.getStatus())
                    .build();
            orderItemList.add(orderItemDto);
        }

        //회원 주문 아이템 주문 상태 조회



        model.addAttribute("member", member);
        model.addAttribute("orderList", orderList);
        model.addAttribute("orderItemList", orderItemList);
        return "/shop/myOrder";
    }

    //주문취소 로직
    @GetMapping("/order/{loginId}/{orderId}")
    public String orderCancel(@PathVariable String loginId, @PathVariable Long orderId) {
        Member member = memberRepository.findByLoginId(loginId);
        log.info("주문번호 = {}", orderId);
        Order order = orderRepository.findById(orderId);

        Long orderMemberId = order.getMember().getId();

        if (member.getId() == orderMemberId) {
            order.cancelOrder();
        }
//        return "redirect://localhost:8080/myOrder/{loginId}";
        return "redirect:/myOrder/{loginId}";
    }

    @GetMapping("/order")
    public String orderPage(@RequestParam("name") String name,
                            Model model) {

        //URL 넘어온 문자를 디코딩
        String itemName = URLDecoder.decode(name, StandardCharsets.UTF_8);

        Item item = itemRepository.findByName(itemName);
        ItemDto itemDto = new ItemDto(item);
        log.info("========================= 주문한 아이템 ============================");
        log.info("이미지 경로 : " + itemDto.getUrl());
        log.info("상품 이름 : " + itemDto.getName());
        log.info("상품 가격 : " + String.valueOf(itemDto.getPrice()));
        model.addAttribute("item", item);
        return "orderPage";
    }
}
