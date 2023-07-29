package com.dio.sacolaapi.model;

import com.dio.sacolaapi.model.enumeration.FormaPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sacola{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itens;

    private double valorTotalSacola;
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    private boolean sacolaFechada;

}
