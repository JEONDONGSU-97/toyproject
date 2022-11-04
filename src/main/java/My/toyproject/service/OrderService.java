package My.toyproject.service;

import My.toyproject.domain.Order;
import My.toyproject.domain.status.OrderStatus;

import java.util.List;

public interface OrderService {

    //주문 생성(여러개)
    public Long order(Long memberId, List<Long> itemId, int count, String size);

    //주문 생성(단건)
    public Long singleOrder(Long memberId, Long itemId, int count, String size);

    //주문 취소
    public void cancel(Long orderId);

    //모든 주문 조회
    public List<Order> findAll();

    //회원 주문 조회
    public List<Order> findById(Long memberId);

    //주문 상태 확인
    public List<OrderStatus> findStatusAll(Long memberId);
}
