package My.toyproject.dto;

import My.toyproject.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CategoryDto {

    private Long id;
    private String name;
//    private Long parentId;
//    private List<CategoryDto> child;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
