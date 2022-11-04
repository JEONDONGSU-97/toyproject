package My.toyproject.serviceImpl;

import My.toyproject.domain.Address;
import My.toyproject.domain.Item;
import My.toyproject.domain.Order;
import My.toyproject.domain.Member;
import My.toyproject.domain.status.OrderStatus;
import My.toyproject.exception.OutOfStockException;
import My.toyproject.repository.ItemRepository;
import My.toyproject.repository.OrderRepository;
import My.toyproject.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceImplTest {

    @PersistenceContext EntityManager em;

    @Autowired OrderServiceImpl orderServiceImpl;
    @Autowired OrderRepository orderRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired ItemRepository itemRepository;

    @Test
    @DisplayName("상품 주문")
    @Rollback(value = false)
    public void order() throws Exception {
        //given
        Member user = createUser("user1", "010-xxxx-xxxx", new Address("1234", "서울 용산구 독서당로 6", "xx아파트 xx동 xx호"));

        Item item1 = createItem("아디다스 운동화", 50000, 500);
        Item item2 = createItem("나이키 운동화", 60000, 100);

        int count = 2;
        String size = "260";

        List<Long> itemList = new ArrayList<>();

        itemList.add(item1.getId());
        itemList.add(item2.getId());

        //when
        Long orderId = orderServiceImpl.order(user.getId(), itemList, count, size);
        Order order = orderRepository.findById(orderId);

        //then
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.READY); //주문 상태
        assertThat(item1.getStockQuantity()).isEqualTo(498); //아디다스 운동화 재고
        assertThat(item2.getStockQuantity()).isEqualTo(98); //나이키 운동화 재고
        assertThat(order.totalOrderPrice()).isEqualTo((item1.getPrice()*count) + (item2.getPrice()*count)); //주문 가격
    }

    @Test
    @DisplayName("주문 취소")
    @Rollback(value = false)
    public void cancel() throws Exception {
        //given
        Item item = createItem("리복", 40000, 10);
        Member user = Member.builder()
//                .id(2L)
                .name("user2")
                .loginId("ZZanggu")
                .mobile("010-xxxx-xxxx")
                .address(new Address("1234", "서울 용산구 독서당로 6", "xx아파트 xx동 xx호"))
                .build();

        memberRepository.save(user);

        //when

        //then

    }

    private Item createItem(String name, int price, int count) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(count);
        itemRepository.save(item);
        return item;
    }

    private Member createUser(String name, String mobile, Address address) {
        Member user = Member.builder()
                .name(name)
                .mobile(mobile)
                .address(address)
                .build();
        memberRepository.save(user);
        return user;
    }
}