package br.com.fiap.bankGestor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.bankGestor.model.Conta;

@RestController
public class ContaController {
    
     private final Logger log = LoggerFactory.getLogger(getClass());

     private List<Conta> repository = new ArrayList<>();

     @PostMapping("/conta")
     public ResponseEntity<Conta> createConta(@RequestBody Conta conta){
        log.info("Cadastrando conta de: " + conta.getNome());
        repository.add(conta);
        return ResponseEntity.status(201).body(conta);
     }

     @GetMapping("/conta")
    public List<Conta> index(){ 
        log.info("buscando todas categorias");
        return repository;
    }


}
