package My.toyproject.controller.shop;

import My.toyproject.domain.Category;
import My.toyproject.domain.Item;
import My.toyproject.dto.CategoryDto;
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
@RequestMapping("/shop/top")
public class TopController {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("긴팔")
    public String longSleeve(Model model) {
        List<Item> items = itemRepository.findCategoryItemByName("긴팔");

        //엔티티 -> DTO
        List<ItemDto> collect = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);
        return "shop/top/longSleeve";
    }

    @GetMapping("니트")
    public String knit(Model model) {
        List<Item> items = itemRepository.findCategoryItemByName("니트");

        //엔티티 -> DTO
        List<ItemDto> collect = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);
        return "shop/top/knit";
    }

    @GetMapping("가디건")
    public String cardigan(Model model) {
        List<Item> items = itemRepository.findCategoryItemByName("가디건");

        //엔티티 -> DTO
        List<ItemDto> collect = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);
        return "shop/top/cardigan";
    }

    @GetMapping("반팔")
    public String shortSleeve(Model model) {
        List<Item> items = itemRepository.findCategoryItemByName("반팔");

        //엔티티 -> DTO
        List<ItemDto> collect = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);
        return "shop/top/shortSleeve";
    }
}
