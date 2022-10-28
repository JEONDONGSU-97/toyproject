package My.toyproject.serviceImpl;

import My.toyproject.domain.*;
import My.toyproject.domain.status.DeliveryStatus;
import My.toyproject.domain.status.OrderStatus;
import My.toyproject.repository.ItemRepository;
import My.toyproject.repository.OrderRepository;
import My.toyproject.repository.MemberRepository;
import My.toyproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    /**
     *  비즈니스 로직
     */
    @Override
    @Transactional
    public Long order(Long memberId, List<Long> itemId, int count) {

        //회원 조회
        Member member = memberRepository.findById(memberId);

        //상품 조회
        List<OrderItem> orderItems = new ArrayList<>();

        for (int i = 0; i < itemId.size(); i++) {
            Item findItem = itemRepository.findById(itemId.get(i));

            //주문 상품 생성
            OrderItem orderItem = OrderItem.createOrderItem(findItem, findItem.getPrice(), count);
            orderItems.add(orderItem);
        }

        //배송 정보 생성
        Delivery delivery = new Delivery();
        delivery.setStatus(DeliveryStatus.READY);
        delivery.setAddress(member.getAddress());

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItems);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    @Override
    public void cancel(Long orderId) {
        //주문 조회
        Order order = orderRepository.findById(orderId);

        //주문 취소
        order.cancelOrder();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findById(Long memberId) {
        return null;
    }

    @Override
    public List<OrderStatus> findStatusAll(Long memberId) {
        return null;
    }
}
