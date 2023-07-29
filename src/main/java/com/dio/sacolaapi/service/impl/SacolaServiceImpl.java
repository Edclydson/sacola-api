package com.dio.sacolaapi.service.impl;

import com.dio.sacolaapi.handler.SacolaException;
import com.dio.sacolaapi.handler.SacolaVaziaException;
import com.dio.sacolaapi.model.enumeration.FormaPagamento;
import com.dio.sacolaapi.model.Item;
import com.dio.sacolaapi.model.Sacola;
import com.dio.sacolaapi.repository.ItemRepository;
import com.dio.sacolaapi.repository.SacolaRepository;
import com.dio.sacolaapi.resource.dto.ItemDTO;
import com.dio.sacolaapi.service.SacolaService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SacolaServiceImpl implements SacolaService{

    private final SacolaRepository sacolaRepository;
    private final ItemRepository itemRepository;

    public SacolaServiceImpl(SacolaRepository sacolaRepository, ItemRepository itemRepository) {
        this.sacolaRepository = sacolaRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Sacola verSacola(UUID id_sacola) {
        return sacolaRepository.findById(id_sacola)
                .orElseThrow(
                        () -> new SacolaException("Sacola não encontrada"));
    }

    @Override
    public Sacola fecharSacola(UUID id_sacola, String formaPagamento) {
        Sacola sacola = verSacola(id_sacola);
        if(sacola.getItens().isEmpty()){
            throw new SacolaVaziaException("Sacola vazia");
        }
        // CRIAR VALIDAÇAO PARA FORMA DE PAGAMENTO
        formaPagamento = formaPagamento.toUpperCase();
        sacola.setFormaPagamento(FormaPagamento.valueOf(formaPagamento));
        sacola.setSacolaFechada(true);
        return sacolaRepository.save(sacola);
    }

    @Override
    public Item adicionarItem(ItemDTO itemDTO) {
        return null;
    }
}
