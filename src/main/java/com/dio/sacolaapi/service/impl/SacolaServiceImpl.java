package com.dio.sacolaapi.service.impl;

import com.dio.sacolaapi.handler.SacolaException;
import com.dio.sacolaapi.handler.SacolaFechadaException;
import com.dio.sacolaapi.handler.SacolaVaziaException;
import com.dio.sacolaapi.model.Item;
import com.dio.sacolaapi.model.Restaurante;
import com.dio.sacolaapi.model.Sacola;
import com.dio.sacolaapi.model.enumeration.FormaPagamento;
import com.dio.sacolaapi.repository.ItemRepository;
import com.dio.sacolaapi.repository.ProdutoRepository;
import com.dio.sacolaapi.repository.SacolaRepository;
import com.dio.sacolaapi.resource.dto.ItemDTO;
import com.dio.sacolaapi.service.SacolaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SacolaServiceImpl implements SacolaService{

    private final SacolaRepository sacolaRepository;
    private final ItemRepository itemRepository;
    private final ProdutoRepository produtoRepository;

    public SacolaServiceImpl(SacolaRepository sacolaRepository, ItemRepository itemRepository, ProdutoRepository produtoRepository) {
        this.sacolaRepository = sacolaRepository;
        this.itemRepository = itemRepository;
        this.produtoRepository = produtoRepository;
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
        Sacola sacola = verSacola(itemDTO.getIdSacola());
        if(sacola.isSacolaFechada()){
            throw new SacolaFechadaException("Sacola fechada");
        }
        Item itemParaInserir = Item.builder()
                .quantidade(itemDTO.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDTO.getIdProduto()).orElseThrow(
                        () -> new SacolaException("Produto não encontrado")))
                .build();

        List<Item> itens = sacola.getItens();
        if(itens.isEmpty()){
            itens.add(itemParaInserir);
        }
        else{
            Restaurante restauranteAtual = itens.get(0).getProduto().getRestaurante();
            Restaurante restauranteNovo = itemParaInserir.getProduto().getRestaurante();
            if(restauranteAtual.equals(restauranteNovo)){
                itens.add(itemParaInserir);
            }
            else{
                throw new SacolaException("Não é possível adicionar produtos de restaurantes diferentes");
            }
        }
        sacolaRepository.save(sacola);
        return itemRepository.save(itemParaInserir);
    }
}
