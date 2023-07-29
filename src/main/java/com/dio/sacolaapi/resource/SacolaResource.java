package com.dio.sacolaapi.resource;


import com.dio.sacolaapi.model.Sacola;
import com.dio.sacolaapi.resource.dto.ItemDTO;
import com.dio.sacolaapi.service.impl.SacolaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/sacola")
public class SacolaResource{

    private final SacolaServiceImpl sacolaService;

    public SacolaResource(SacolaServiceImpl sacolaService) {
        this.sacolaService = sacolaService;
    }

    @GetMapping("/{id_sacola}")
    public ResponseEntity<Sacola> verSacola(@PathVariable String id_sacola){
        Sacola response = sacolaService.verSacola(UUID.fromString(id_sacola));
        if(response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/fechar/{id_sacola}")
    public ResponseEntity<Sacola> fecharSacola(@PathVariable String id_sacola, @RequestParam("formaPagamento") String formaPagamento){
        Sacola response = sacolaService.fecharSacola(UUID.fromString(id_sacola), formaPagamento);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity adicionarItem(@RequestBody ItemDTO item){
        sacolaService.adicionarItem(item);
        return ResponseEntity.noContent().build();
    }

}
