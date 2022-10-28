package My.toyproject.service;

import My.toyproject.domain.OrderItem;

import java.util.List;

public interface OrderItemService {

    //주문 상품 조회
    public List<OrderItem> findAll();

    //주문 상품 단건 조회
    public OrderItem findById(Long orderItemId);

}
