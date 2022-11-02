package My.toyproject.serviceImpl;

import My.toyproject.domain.Cart;
import My.toyproject.domain.CartItem;
import My.toyproject.domain.Item;
import My.toyproject.domain.Member;
import My.toyproject.repository.CartItemRepository;
import My.toyproject.repository.CartRepository;
import My.toyproject.repository.ItemRepository;
import My.toyproject.repository.MemberRepository;
import My.toyproject.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;

    /**
     *  비즈니스 로직
     */
    @Override
    @Transactional
    public Long cart(Long memberId, List<Long> itemId, int count, String size) {

        //회원 조회
        Member member = memberRepository.findById(memberId);

        //장바구니 아이템 리스트 생성
        List<CartItem> cartItems  = new ArrayList<>();

        for (int i = 0; i < itemId.size(); i++) {
            Item findItem = itemRepository.findById(itemId.get(i));

            //장바구니 아이템 담기
            CartItem cartItem = CartItem.createCartItem(findItem, findItem.getPrice(), count, size);
            cartItems.add(cartItem);
        }

        //장바구니 생성 (회원당 장바구니 1개)
        Cart cart = Cart.createCart(member, cartItems);

        //장바구니 저장
        cartRepository.save(cart);

        return cart.getId();
    }

    @Override
    public Long singleCart(Long memberId, Long itemId, int count, String size) {

        //회원 조회
        Member member = memberRepository.findById(memberId);

        //장바구니 아이템 리스트 생성
        List<CartItem> cartItems  = new ArrayList<>();

        Item findItem = itemRepository.findById(itemId);

        //장바구니 아이템 담기
        CartItem cartItem = CartItem.createCartItem(findItem, findItem.getPrice(), count, size);
        cartItem.setTotalPrice(findItem.getPrice() * count);
        cartItems.add(cartItem);

        //장바구니 생성 (회원당 장바구니 1개)
        Cart cart = Cart.createCart(member, cartItems);

        //장바구니 저장
        cartRepository.save(cart);

        return cart.getId();
    }

    @Override
    public Cart findByMemberId(Long memberId) {
        return cartRepository.findById(memberId);
    }
}
