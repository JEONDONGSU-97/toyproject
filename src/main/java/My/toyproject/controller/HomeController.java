package My.toyproject.controller;

import My.toyproject.domain.Item;
import My.toyproject.domain.ItemImage;
import My.toyproject.domain.Member;
import My.toyproject.dto.ItemDto;
import My.toyproject.repository.ItemRepository;
import My.toyproject.repository.MemberRepository;
import My.toyproject.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

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
        return "myCart";
    }

    @GetMapping("/myOrder")
    public String myOrder() {
        return "myOrder";
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
