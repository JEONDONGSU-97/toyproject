package My.toyproject.controller.shop;

import My.toyproject.domain.Category;
import My.toyproject.domain.Item;
import My.toyproject.dto.CategoryDto;
import My.toyproject.dto.ItemDto;
import My.toyproject.repository.CategoryRepository;
import My.toyproject.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop/outer")
public class OuterController {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/자켓")
    public String jacket(Model model) {
        List<Item> items = itemRepository.findCategoryItemByName("자켓");

        //엔티티 -> DTO
        List<ItemDto> collect = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);
        return "shop/outer/jacket";
    }

    @GetMapping("/수트")
    public String suit(Model model) {
        List<Item> items = itemRepository.findCategoryItemByName("수트");

        //엔티티 -> DTO
        List<ItemDto> collect = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);
        return "shop/outer/suit";
    }

    @GetMapping("/코트")
    public String coat(Model model) {
        List<Item> items = itemRepository.findCategoryItemByName("코트");

        //엔티티 -> DTO
        List<ItemDto> collect = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);
        return "shop/outer/coat";
    }

    @GetMapping("/패딩")
    public String padding(Model model) {
        List<Item> items = itemRepository.findCategoryItemByName("패딩");

        //엔티티 -> DTO
        List<ItemDto> collect = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);
        return "shop/outer/padding";
    }


}
