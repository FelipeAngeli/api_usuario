package com.felipe.API_usuario.business;

import com.felipe.API_usuario.infrastructure.clients.ViaCepDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private  final ViaCepService client;

    public ViaCepDTO buscaDadosEndereco(String cep) {
        try{
            return client.buscaDadosEndereco(processarCep(cep));
        }catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Erro: ", ex);
        }
    }

    private String processarCep(String cep) {
        String cepFormatado = cep.replace(" ", "").
                replace("-", "");

        if(!cepFormatado.matches("[0-9+]") || !Objects.equals( cepFormatado.length(),8)){
            throw new  IllegalArgumentException("CEP invalido");
        }
        return cepFormatado;
    }
}
