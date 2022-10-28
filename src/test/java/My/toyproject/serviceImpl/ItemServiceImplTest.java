package My.toyproject.serviceImpl;

import My.toyproject.domain.Item;
import My.toyproject.exception.OverStockException;
import My.toyproject.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ItemServiceImplTest {

    @Autowired ItemService itemService;

    @Test
    @DisplayName("상품 등록")
    @Rollback(value = false) //데이터베이스 내용 확인을 위함
    public void addStock() {
        //given
        Item item = new Item();
        item.setName("아디다스 운동화");
        item.setPrice(50000);
        item.setStockQuantity(500);

        //when
        Long itemId = itemService.saveItem(item);

        //then
        Assertions.assertEquals(item, itemService.findById(itemId));
    }

    //과도하게 많은 재고 수량을 제재하기 위함.
    //관리자단
    @Test
    @DisplayName("상품 재고 수량 초과")
    @Rollback(value = false) //데이터베이스 내용 확인을 위함
    public void addStock_error() {
        //given
        Item item = new Item();
        item.setName("아디다스 운동화");
        item.setPrice(50000);
        item.setStockQuantity(500);
        Long itemId = itemService.saveItem(item);

        //when, then
        Item findItem = itemService.findById(itemId);
        assertThrows(OverStockException.class, () -> findItem.addStock(1));
    }

}