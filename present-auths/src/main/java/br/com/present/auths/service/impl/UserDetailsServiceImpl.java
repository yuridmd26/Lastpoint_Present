package br.com.present.auths.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<UsuarioEntity> usuario = usuarioRepository.findByLogin(username);
//		if(usuario.isEmpty()) {
//			throw new UsernameNotFoundException("detalheUsuarioServiceImpl.err.naoEncontrouUsuario");
//		}
//		return new UserDetailsImpl(usuario.get());
		return null;
	}

}