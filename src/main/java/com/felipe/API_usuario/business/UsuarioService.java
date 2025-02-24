package com.felipe.API_usuario.business;

import com.felipe.API_usuario.business.converter.UsuarioConverter;
import com.felipe.API_usuario.business.dto.UsuarioDTO;
import com.felipe.API_usuario.infrastructure.entity.Usuario;
import com.felipe.API_usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private  final UsuarioRepository usuarioRepository;
    private  final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }
}
