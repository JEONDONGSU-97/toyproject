package My.toyproject.controller.shop;

import My.toyproject.domain.Item;
import My.toyproject.dto.ItemDto;
import My.toyproject.repository.CategoryRepository;
import My.toyproject.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop/bottoms")
public class BottomsController {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("슬렉스")
    public String slacks(Model model) {
        List<Item> items = itemRepository.findCategoryItemByName("슬렉스");

        //엔티티 -> DTO
        List<ItemDto> collect = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);
        return "shop/bottoms/slacks";
    }

    @GetMapping("데님")
    public String denim(Model model) {
        List<Item> items = itemRepository.findCategoryItemByName("데님");

        //엔티티 -> DTO
        List<ItemDto> collect = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);
        return "shop/bottoms/denim";
    }

    @GetMapping("트레이닝")
    public String sweatPants(Model model) {
        List<Item> items = itemRepository.findCategoryItemByName("트레이닝");

        //엔티티 -> DTO
        List<ItemDto> collect = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);
        return "shop/bottoms/sweatPants";
    }

    @GetMapping("반바지")
    public String shorts(Model model) {
        List<Item> items = itemRepository.findCategoryItemByName("반바지");

        //엔티티 -> DTO
        List<ItemDto> collect = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);
        return "shop/bottoms/shorts";
    }
}
