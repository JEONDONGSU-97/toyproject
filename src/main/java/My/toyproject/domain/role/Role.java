package My.toyproject.domain.role;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public enum Role {

    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    Role(String value) {
        this.value = value;
    }

    private String value;
}
