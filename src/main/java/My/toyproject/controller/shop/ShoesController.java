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
@RequestMapping("/shop/shoes")
public class ShoesController {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("로퍼")
    public String loafers(Model model) {
        List<Item> items = itemRepository.findCategoryItemByName("로퍼");

        //엔티티 -> DTO
        List<ItemDto> collect = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);
        return "shop/shoes/loafers";
    }

    @GetMapping("더비슈즈")
    public String derbyShoes(Model model) {
        List<Item> items = itemRepository.findCategoryItemByName("더비슈즈");

        //엔티티 -> DTO
        List<ItemDto> collect = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);
        return "shop/shoes/derbyShoes";
    }

    @GetMapping("스니커즈")
    public String sneakers(Model model) {
        List<Item> items = itemRepository.findCategoryItemByName("스니커즈");

        //엔티티 -> DTO
        List<ItemDto> collect = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);
        return "shop/shoes/sneakers";
    }

    @GetMapping("슬리퍼")
    public String slippers(Model model) {
        List<Item> items = itemRepository.findCategoryItemByName("슬리퍼");

        //엔티티 -> DTO
        List<ItemDto> collect = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);
        return "shop/shoes/slippers";
    }
}
