package My.toyproject.service;

import My.toyproject.domain.Item;

import java.util.List;

public interface ItemService {

    //상품 저장
    public Long saveItem(Item item);

    //상품 단건 조회
    public Item findById(Long itemId);

    //모든 상품 조회
    public List<Item> findAll();
}
