package My.toyproject.controller.shop;

import My.toyproject.domain.Item;
import My.toyproject.dto.ItemDto;
import My.toyproject.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SalesController {

    private final ItemRepository itemRepository;

    @GetMapping("/shop/sales")
    public String sales(Model model) {

        List<Item> salesItemList = new ArrayList<>();

        Item salesItem1 = itemRepository.findByName("USA 절개 오버핏 티셔츠");
        Item salesItem2 = itemRepository.findByName("워셔블 드랍 하찌 니트");
        Item salesItem3 = itemRepository.findByName("메리노울 꽈배기 집업 가디건");
        Item salesItem4 = itemRepository.findByName("엘르 스트라이프 카라 니트");
        Item salesItem5 = itemRepository.findByName("윈디 레귤러 벤딩 슬렉스");
        Item salesItem6 = itemRepository.findByName("키퍼 원턱 와이드 데님팬츠");
        Item salesItem7 = itemRepository.findByName("베른 2way 스웻팬츠");
        Item salesItem8 = itemRepository.findByName("지엘 트레이닝 반바지");

        salesItemList.add(salesItem1);
        salesItemList.add(salesItem2);
        salesItemList.add(salesItem3);
        salesItemList.add(salesItem4);
        salesItemList.add(salesItem5);
        salesItemList.add(salesItem6);
        salesItemList.add(salesItem7);
        salesItemList.add(salesItem8);

        List<ItemDto> salesItemDto = salesItemList.stream().map(o -> new ItemDto(o)).collect(Collectors.toList());

        model.addAttribute("items", salesItemDto);
        return "shop/sales/sales";
    }
}
