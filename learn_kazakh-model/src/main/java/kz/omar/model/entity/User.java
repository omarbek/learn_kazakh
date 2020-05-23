package kz.omar.model.entity;

import kz.omar.utils.NotNullUtils;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-25
 * @project learn_kazakh
 */
@Entity
@Table(name = "USERS")
@Data
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "username")
    @NotNull(message = NotNullUtils.Constants.USERNAME_MUST_NOT_BE_NULL_VALUE)
    private String username;
    
    @Column(name = "password")
    @NotNull(message = NotNullUtils.Constants.PASSWORD_MUST_NOT_BE_NULL_VALUE)
    private String password;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @NotNull(message = NotNullUtils.Constants.ROLE_MUST_NOT_BE_NULL_VALUE)
    private Role role;
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>();
    }
    
    public boolean isAccountNonExpired() {
        return true;
    }
    
    public boolean isAccountNonLocked() {
        return true;
    }
    
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    public boolean isEnabled() {
        return true;
    }
    
}
