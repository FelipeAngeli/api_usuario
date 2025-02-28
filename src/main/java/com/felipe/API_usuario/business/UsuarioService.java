package com.felipe.API_usuario.business;

import com.felipe.API_usuario.business.converter.UsuarioConverter;
import com.felipe.API_usuario.business.dto.EnderecoDTO;
import com.felipe.API_usuario.business.dto.TelefoneDTO;
import com.felipe.API_usuario.business.dto.UsuarioDTO;
import com.felipe.API_usuario.infrastructure.entity.Endereco;
import com.felipe.API_usuario.infrastructure.entity.Telefone;
import com.felipe.API_usuario.infrastructure.entity.Usuario;
import com.felipe.API_usuario.infrastructure.exceptions.ConflitExceptions;
import com.felipe.API_usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.felipe.API_usuario.infrastructure.repository.EnderecoRepository;
import com.felipe.API_usuario.infrastructure.repository.TelefoneRepository;
import com.felipe.API_usuario.infrastructure.repository.UsuarioRepository;
import com.felipe.API_usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private  final UsuarioRepository usuarioRepository;
    private  final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;


    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public void emailExiste(String email){
        try {
            boolean existe = verificaEmailExistente(email);
            if(existe){
                throw new ConflitExceptions("Email já cadastrado"+ email);
            }
        } catch (ConflitExceptions e) {
            throw new RuntimeException("Email já cadastrado" + e.getCause());
        }
    }

    public  boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email){
        try {
            return usuarioConverter.paraUsuarioDTO(usuarioRepository.findByEmail(email).orElseThrow(
                    () -> new ResourceNotFoundException("Email não encontrado " + email)));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Email não encontrado " + email);
        }
    }

    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto){
        // Aqui buscamos o email do usuario atravez do token (para tirar a obrigatoriedade do email)
       String email = jwtUtil.extrairEmailToken(token.substring(7));
       //Criptografia de senha
       dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()): null);
       // Buscamos os dados do usuario no banco de dados
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
            new ResourceNotFoundException("Email não localizado"));
        // Mesclou os dados que recebemos na requisiçao DTO com os dados do banco de dados
        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);
        // Salvou os dados do usuario convertido e depois pegou o retorno e converteu para usuarioDTO
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public EnderecoDTO atualizaEndereco(Long idEndereco, EnderecoDTO enderecoDTO){
        Endereco entity = enderecoRepository.findById(idEndereco).orElseThrow(() ->
                new ResourceNotFoundException("Id não encontrado " + idEndereco));

        Endereco endereco = usuarioConverter.updateEndereco(enderecoDTO, entity);
        return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(endereco));
    }

    public TelefoneDTO atualizaTelefone(Long idTelefone, TelefoneDTO telefoneDTO){
        Telefone entity = telefoneRepository.findById(idTelefone).orElseThrow( () ->
                new ResourceNotFoundException("Id não encontrado " + idTelefone));

        Telefone telefone = usuarioConverter.updateTelefone(telefoneDTO, entity);
        return  usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));
    }

}
