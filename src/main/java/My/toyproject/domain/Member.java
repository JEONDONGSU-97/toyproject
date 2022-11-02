package My.toyproject.domain;

import My.toyproject.domain.role.Role;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Slf4j
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //무분별한 객체 생성을 막기 위함
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Cart> carts = new ArrayList<>();

    private String name;
    @Column(unique = true)
    private String loginId;
    private String password;
    private String mobile;

    @Column(unique = true)
    private String email;

    private boolean enabled;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(Long id, String name, String loginId, String password, String mobile, String email, Address address, boolean enabled, Role role) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.enabled = enabled;
        this.role = role;
    }
}
