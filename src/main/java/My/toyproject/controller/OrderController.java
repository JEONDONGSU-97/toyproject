package My.toyproject.controller;

import My.toyproject.domain.Item;
import My.toyproject.dto.ItemDto;
import My.toyproject.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class OrderController {

    private final ItemRepository itemRepository;

    @GetMapping("/outer/order/{name}")
    public String order(@PathVariable String name, Model model) {

        Item item = itemRepository.findByName(name);
        ItemDto itemDto = new ItemDto(item);

        model.addAttribute("item", itemDto);
        return "/shop/orderPage";
    }

    @GetMapping("/top/order/{name}")
    public String top(@PathVariable String name, Model model) {

        Item item = itemRepository.findByName(name);
        ItemDto itemDto = new ItemDto(item);

        model.addAttribute("item", itemDto);
        return "/shop/orderPage";
    }

    @GetMapping("/bottoms/order/{name}")
    public String bottoms(@PathVariable String name, Model model) {

        Item item = itemRepository.findByName(name);
        ItemDto itemDto = new ItemDto(item);

        model.addAttribute("item", itemDto);
        return "/shop/orderPage";
    }

    @GetMapping("/shoes/order/{name}")
    public String shoes(@PathVariable String name, Model model) {

        Item item = itemRepository.findByName(name);
        ItemDto itemDto = new ItemDto(item);

        model.addAttribute("item", itemDto);
        return "/shop/orderPage";
    }

    @GetMapping("/sales/order/{name}")
    public String sales(@PathVariable String name, Model model) {

        Item item = itemRepository.findByName(name);
        ItemDto itemDto = new ItemDto(item);

        model.addAttribute("item", itemDto);
        return "/shop/orderPage";
    }
}
