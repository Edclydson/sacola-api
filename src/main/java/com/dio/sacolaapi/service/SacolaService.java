package com.dio.sacolaapi.service;

import com.dio.sacolaapi.model.Item;
import com.dio.sacolaapi.model.Sacola;
import com.dio.sacolaapi.resource.dto.ItemDTO;

import java.util.UUID;

public interface SacolaService{

    Sacola verSacola(UUID id_sacola);
    Sacola fecharSacola(UUID id_sacola, String formaPagamento);
    Item adicionarItem(ItemDTO itemDTO);
}
