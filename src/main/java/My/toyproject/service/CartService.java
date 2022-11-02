package My.toyproject.service;

import My.toyproject.domain.Cart;

import java.util.List;

public interface CartService {

    //장바구니 생성
    public Long cart(Long memberId, List<Long> itemId, int count, String size);

    //장바구니 생성
    public Long singleCart(Long memberId, Long itemId, int count, String size);

    //장바구니 조회
    public Cart findByMemberId(Long memberId);
}
