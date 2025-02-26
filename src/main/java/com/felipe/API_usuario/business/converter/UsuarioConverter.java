package com.felipe.API_usuario.business.converter;

import com.felipe.API_usuario.business.dto.EnderecoDTO;
import com.felipe.API_usuario.business.dto.TelefoneDTO;
import com.felipe.API_usuario.business.dto.UsuarioDTO;
import com.felipe.API_usuario.infrastructure.entity.Endereco;
import com.felipe.API_usuario.infrastructure.entity.Telefone;
import com.felipe.API_usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        if (usuarioDTO == null) {
            return null;
        }

        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .endereco(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS){
        return Optional.ofNullable(enderecoDTOS)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::paraEndereco)
                .collect(Collectors.toList());
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        if (enderecoDTO == null) {
            return null;
        }
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS){
        return Optional.ofNullable(telefoneDTOS)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::paraTelefone)
                .collect(Collectors.toList());
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        if (telefoneDTO == null) {
            return null;
        }
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    //---

    public UsuarioDTO paraUsuarioDTO(Usuario usuario){
        if (usuario == null) {
            return null;
        }

        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paraListaEnderecoDTO(usuario.getEndereco()))
                .telefones(paraListaTelefoneDTO(usuario.getTelefones()))
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecos){
        return Optional.ofNullable(enderecos)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::paraEnderecoDTO)
                .collect(Collectors.toList());
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco){
        if (endereco == null) {
            return null;
        }
        return EnderecoDTO.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefones){
        return Optional.ofNullable(telefones)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::paraTelefoneDTO)
                .collect(Collectors.toList());
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone){
        if (telefone == null) {
            return null;
        }
        return TelefoneDTO.builder()
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }

    public  Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario entity){
        return Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : entity.getNome())
                .id(entity.getId())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : entity.getSenha())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : entity.getEmail())
                .endereco(entity.getEndereco())
                .telefones(entity.getTelefones())
                .build();
    }

}
