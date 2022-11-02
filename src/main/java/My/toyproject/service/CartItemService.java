package My.toyproject.service;

import My.toyproject.domain.CartItem;

import java.util.List;

public interface CartItemService {

    //장바구니 아이템 모두 조회
    public List<CartItem> findAll();

    //회원 장바구니 아이템 조회
    public List<CartItem> findByCartId(Long cartId);

    //장바구니 아이템 단건 조회
    public CartItem findById(Long cartItemId);
}
