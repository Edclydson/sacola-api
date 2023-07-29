package com.dio.sacolaapi.resource.dto;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Embeddable
public class ItemDTO{
    private UUID idProduto;
    private int quantidade;
    private UUID idSacola;

}
