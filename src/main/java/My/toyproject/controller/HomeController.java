package My.toyproject.controller;

import My.toyproject.domain.*;
import My.toyproject.domain.status.DeliveryStatus;
import My.toyproject.dto.CartItemDto;
import My.toyproject.dto.ItemDto;
import My.toyproject.dto.MemberDto;
import My.toyproject.dto.OrderItemDto;
import My.toyproject.repository.*;
import My.toyproject.security.SecurityUser;
import My.toyproject.serviceImpl.CartItemServiceImpl;
import My.toyproject.serviceImpl.CartServiceImpl;
import My.toyproject.serviceImpl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    //회원 장바구니(회원 아이디 == 장바구니 아이디)
    private final CartRepository cartRepository;
    //회원 장바구니 아이템
    private final CartItemRepository cartItemRepository;
    private final CartServiceImpl cartService;
    private final CartItemServiceImpl cartItemService;

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

    //장바구니 로직
    @GetMapping("/myCart/{name}/{count}/{size}")
    public String myCart(@PathVariable String name,
                         @PathVariable int count,
                         @PathVariable String size,
                         Model model) {

        Item item = itemRepository.findByName(name);

        //사용자 정보 가져오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = ((UserDetails) principal).getUsername();
        String password = ((UserDetails) principal).getPassword();

        Member member = memberRepository.findByLoginId(loginId);

        MemberDto memberDto = MemberDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .mobile(member.getMobile())
                .zipCode(member.getAddress().getZipCode())
                .street(member.getAddress().getStreet())
                .detail(member.getAddress().getDetail())
                .build();

        //item => CartItemDto
        CartItemDto cartItemDto = CartItemDto.builder()
                .name(item.getName())
                .totalPrice(item.getPrice() * count)
                .price(item.getPrice())
                .count(count)
                .size(size)
                .url(item.getImage().getUrl())
                .build();

        log.info("==================총 가격=============================");
        log.info("합계 = {}", item.getPrice() * count);

        //회원 장바구니 로직
        cartService.singleCart(member.getId(), item.getId(), count, size);
        List<Cart> memberCartList = cartRepository.findByMemberId(member.getId());
        memberCartList.get(0);

        return "redirect:/myCart/" + loginId;
    }

    //장바구니 아이템들을 순차적으로 출력
    @GetMapping("/myCart/{loginId}")
    public String myCartPage(@PathVariable String loginId, Model model) {

        Member member = memberRepository.findByLoginId(loginId);
        MemberDto memberDto = MemberDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .mobile(member.getMobile())
                .zipCode(member.getAddress().getZipCode())
                .street(member.getAddress().getStreet())
                .detail(member.getAddress().getDetail())
                .build();
        Long memberId = member.getId();

        //회원 장바구니 조회
        List<Cart> cartList = cartRepository.findByMemberId(memberId);

        //회원 장바구니가 생성되지 않을때도 생각을 해야함
        //조건문을 하지 않을 경우 cartList.get(0)에서 Index 0 out of bounds for length 0오류
        if (!cartList.isEmpty()) {

            //회원 장바구니 아이템 조회
            List<CartItemDto> cartItemDtos = new ArrayList<>();

            for (Cart cart : cartList) {
                CartItem cartItem = cartItemRepository.findById(cart.getId());

                CartItemDto cartItemDto = CartItemDto.builder()
                        .cartId(cart.getId())
                        .name(cartItem.getName())
                        .price(cartItem.getPrice())
                        .count(cartItem.getCount())
                        .size(cartItem.getSize())
                        .url(cartItem.getItem().getImage().getUrl())
                        .totalPrice(cartItem.getPrice() * cartItem.getCount())
                        .build();
                cartItemDtos.add(cartItemDto);
            }

            model.addAttribute("memberLoginId", member.getLoginId());
            model.addAttribute("member", memberDto);
            model.addAttribute("cartItemList", cartItemDtos);
            return "/shop/myCart";
        }

        log.info("=============장바구니 목록=====================");
        for (Cart cart : cartList) {
            CartItem cartItem = cartItemRepository.findById(cart.getId());
            log.info("장바구니 상품 = {}", cartItem);
        }

        model.addAttribute("member", member);
        return "/shop/myCartEmpty";
    }

    //장바구니 아이템 삭제 (단건)
    @GetMapping("/cart/{loginId}/{cartId}")
    public String cartEmpty(@PathVariable String loginId, @PathVariable Long cartId) {
        Member member = memberRepository.findByLoginId(loginId);
        Cart cart = cartRepository.findById(cartId);
        cartRepository.deleteCart(cart.getId());
        cart.emptyCart();

        return "redirect:/myCart/{loginId}";
    }

    //장바구니 아이템 삭제 (전부)
    @GetMapping("/cart/{loginId}")
    public String cartAllEmpty(@PathVariable String loginId) {
        Member member = memberRepository.findByLoginId(loginId);
        cartRepository.deleteCartAll(member.getId());

        return "redirect:/myCart/{loginId}";
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
