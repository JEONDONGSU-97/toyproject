package My.toyproject.dto;

import My.toyproject.domain.Category;
import My.toyproject.domain.Item;
import My.toyproject.domain.ItemImage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ItemDto {

    private String name;
    private int price;

    //아이템 이미지를 불러오기 위함
    private String url;

    //orderPage에서 신발만 따로 사이즈 분류
    private String category;

    private int stockQuantity;


    public ItemDto(Item item) {
        name = item.getName();
        price = item.getPrice();
        url = item.getImage().getUrl();
        stockQuantity = item.getStockQuantity();
        category = item.getCategory().getParent().getName();
    }

}
