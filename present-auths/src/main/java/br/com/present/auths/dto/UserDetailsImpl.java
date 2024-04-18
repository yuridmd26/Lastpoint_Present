package br.com.present.auths.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails, Serializable {

	@Serial
	private static final long serialVersionUID = 4755747145743406528L;
	
	//private final UsuarioEntity usuario;
	
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
//		Set<RoleEntity> lRole = usuario.getRoles();
//		Collection<GrantedAuthority> autoridades = new HashSet<>(); 
//		for (RoleEntity umRole : lRole) {
//			autoridades.addAll(umRole.getNome().getAutoridadesConcedidas());
//		}
//		return autoridades;
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		//return usuario.getSenha();
		return "";
	}
	
	@Override
	public String getUsername() {
		//return usuario.getLogin();
		return "";
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}