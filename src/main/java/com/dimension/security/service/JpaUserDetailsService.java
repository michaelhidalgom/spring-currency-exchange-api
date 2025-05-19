package com.dimension.security.service;

import com.dimension.security.model.Rol;
import com.dimension.security.model.Usuario;
import com.dimension.security.repository.IUsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioDao;

    private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioDao.findByUsername(username)
                .orElseThrow(() ->
                        {
                            logger.error("Error en el Login: no existe el usuario '" + username + "' en el sistema!");
                            return new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
                        }
                );

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(),
                true, true, true,
                mapRolesToAuthorities(usuario.getRoles(),username));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Rol> roles, String username) {

        if (roles.isEmpty()){
            logger.error("Error en el Login: Usuario '" + username + "' no tiene roles asignados!");
            throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
        }

        return roles.stream()
                .map(rol -> {
                    logger.info("Role: ".concat(rol.getAuthority()));
                    return new SimpleGrantedAuthority(rol.getAuthority());}
                )
                .collect(Collectors.toList());
    }
}
