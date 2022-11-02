package My.toyproject.serviceImpl;

import My.toyproject.domain.Cart;
import My.toyproject.domain.CartItem;
import My.toyproject.domain.Item;
import My.toyproject.repository.CartItemRepository;
import My.toyproject.repository.CartRepository;
import My.toyproject.repository.ItemRepository;
import My.toyproject.repository.MemberRepository;
import My.toyproject.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public List<CartItem> findByCartId(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @Override
    public CartItem findById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId);
    }
}
