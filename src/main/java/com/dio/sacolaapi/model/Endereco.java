package com.dio.sacolaapi.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Embeddable
public class Endereco{
    private String cep;
    private String logradouro;
}
