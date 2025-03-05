package com.felipe.API_usuario.business.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTO {
    private Long id;
    private String numero;
    private String ddd;
}
