package br.com.present.auths.config.type.roles;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.present.auths.config.type.permissions.IPermissionType;
import br.com.present.commons.util.PresentStreamUtils;

@Getter
public enum RolesType {
    EVERYONE,
    ADMIN,
    PROF,
    ALUNO;

    private final Set<IPermissionType> permissions = new HashSet<>();

    public void addPermissions(Set<IPermissionType> permissions) {
        this.permissions.addAll(permissions);
    }

    public Set<GrantedAuthority> getGrantedAuthorities(){
        Set<GrantedAuthority> authorities = new HashSet<>();
        
        PresentStreamUtils.collectionToStream(getPermissions())
        		.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getValue())));
        return authorities;
    }
}