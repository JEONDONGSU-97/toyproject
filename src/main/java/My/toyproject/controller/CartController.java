package My.toyproject.controller;

import My.toyproject.domain.Item;
import My.toyproject.dto.ItemDto;
import My.toyproject.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class CartController {

    private final ItemRepository itemRepository;

    @GetMapping("/cart/{name}")
    public String cart(@PathVariable String name, Model model) {

        Item item = itemRepository.findByName(name);
        ItemDto itemDto = new ItemDto(item);

        model.addAttribute("item", itemDto);
        return "/shop/myCart";
    }
}
