package My.toyproject.controller.shop;

import My.toyproject.domain.Category;
import My.toyproject.dto.CategoryDto;
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
@RequestMapping("/shop")
public class CategoryController {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/outer")
    public String outer(Model model) {
        List<Category> categories = categoryRepository.findByParentId(1L);

        //엔티티 -> DTO
        List<CategoryDto> collect = categories.stream()
                .map(o -> new CategoryDto(o))
                .collect(Collectors.toList());

        model.addAttribute("categories", collect);
        return "shop/outer/outer";
    }

    @GetMapping("/top")
    public String top(Model model) {
        List<Category> categories = categoryRepository.findByParentId(2L);

        //엔티티 -> DTO
        List<CategoryDto> collect = categories.stream()
                .map(o -> new CategoryDto(o))
                .collect(Collectors.toList());

        model.addAttribute("categories", collect);
        return "shop/top/top";
    }

    @GetMapping("/bottoms")
    public String bottoms(Model model) {
        List<Category> categories = categoryRepository.findByParentId(3L);

        //엔티티 -> DTO
        List<CategoryDto> collect = categories.stream()
                .map(o -> new CategoryDto(o))
                .collect(Collectors.toList());

        model.addAttribute("categories", collect);
        return "shop/bottoms/bottoms";
    }

    @GetMapping("/shoes")
    public String shoes(Model model) {
        List<Category> categories = categoryRepository.findByParentId(4L);

        //엔티티 -> DTO
        List<CategoryDto> collect = categories.stream()
                .map(o -> new CategoryDto(o))
                .collect(Collectors.toList());

        model.addAttribute("categories", collect);
        return "shop/shoes/shoes";
    }
}
