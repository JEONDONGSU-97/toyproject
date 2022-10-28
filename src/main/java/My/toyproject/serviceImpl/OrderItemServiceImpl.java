package My.toyproject.serviceImpl;

import My.toyproject.domain.OrderItem;
import My.toyproject.repository.OrderItemRepository;
import My.toyproject.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem findById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId);
    }
}
