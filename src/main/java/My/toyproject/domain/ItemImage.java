package My.toyproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "item_image")
@Getter @Setter
public class ItemImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_image_id")
    private Long id;

    @OneToOne(mappedBy = "image", fetch = LAZY)
    private Item item;

    private String name;
    private String url;
}
