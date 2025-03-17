package br.com.fiap.bankGestor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {
    
    @GetMapping("/")
    public String index(){ 
        return "Nome Projeto: Bank Gestor.\nNome: Felipe Levy Stephens Fidelix;\nRM: 556426;";
    }

}
