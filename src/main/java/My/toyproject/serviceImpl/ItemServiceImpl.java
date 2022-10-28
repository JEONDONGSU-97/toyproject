package My.toyproject.serviceImpl;

import My.toyproject.domain.Item;
import My.toyproject.repository.ItemRepository;
import My.toyproject.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//readOnly = true, 데이터 변경, 추가시 오류 발생(오류가 발생 안 할 수도 있다.)
//더티체킹 건너뜀 => 성능향상 => 최적화
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public Long saveItem(Item item) {
        itemRepository.save(item);
        return item.getId();
    }

    @Override
    public Item findById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
