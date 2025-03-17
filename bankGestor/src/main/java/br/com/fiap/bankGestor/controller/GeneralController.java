package br.com.fiap.bankGestor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {
    
    @GetMapping("/")
    public String index(){ 
        return "Nome Projeto: Bank Gestor.\nIntegrante 1: Felipe Levy Stephens Fidelix;\nIntegrante 2: Samir Hage Neto;";
    }

}
