package br.com.fiap.bankGestor.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.bankGestor.model.Conta;

@RestController
public class ContaController {
    
     private final Logger log = LoggerFactory.getLogger(getClass());

     private List<Conta> contas = new ArrayList<>();

     @PostMapping("/conta")
     public <T> ResponseEntity<Conta> createConta(@RequestBody Conta conta){
        Map<String, String> erros = new HashMap<>();

        if (conta.getNome() == null || conta.getNome().isEmpty()) {
            erros.put("nomeTitular", "Nome do titular é obrigatório");
        }
        else if (conta.getCpf() == null || conta.getCpf().isEmpty()) {
            erros.put("cpfTitular", "CPF do titular é obrigatório");
        }
        else if (conta.getDataDeAbertura() != null && conta.getDataDeAbertura().isAfter(LocalDate.now())) {
            erros.put("dataAbertura", "Data de abertura não pode ser no futuro");
        }
        else if (conta.getSaldoInicial() < 0) {
            erros.put("saldo", "Saldo inicial não pode ser negativo");
        }
        else if (conta.getTipo() == null || (!conta.getTipo().equals("corrente") && 
                                         !conta.getTipo().equals("poupança") && 
                                         !conta.getTipo().equals("salário"))) {
            erros.put("tipo", "Tipo deve ser 'corrente', 'poupança' ou 'salário'");
        }

        else if (!erros.isEmpty()) {
            return (ResponseEntity<Conta>) ResponseEntity.badRequest().body((T) erros);
        }
        log.info("Cadastrando conta de: " + conta.getNome());
        contas.add(conta);
        return ResponseEntity.status(201).body(conta);
     }

     @GetMapping("/conta")
    public List<Conta> index(){ 
        log.info("buscando todas as contas");
        return contas;
    }

    @GetMapping("/conta/numero/{numero}")
    public ResponseEntity<Conta> getByNumero(@PathVariable Long numero){
        log.info("buscando conta");
        return ResponseEntity.ok(getContaNumero(numero)); 
    }

    @GetMapping("/conta/cpf/{cpf}")
    public ResponseEntity<Conta> getByCpf(@PathVariable String cpf){
        log.info("buscando conta");
        return ResponseEntity.ok(getContaCpf(cpf)); 
    }

    @DeleteMapping("/conta/{numero}")
    public ResponseEntity<Object> destroy(@PathVariable Long numero){
        log.info("apagando Conta: " + numero);
        Conta contaToDelete = getContaNumero(numero);
        contas.remove(contaToDelete);
        contaToDelete.setStatus(false);
        contas.add(contaToDelete);
        return ResponseEntity.noContent().build();
    }

    

    private Conta getContaNumero(Long numero) {
        return contas.stream().filter(c -> c.getNumero().equals(numero)).findFirst().orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não enocntrada")
        );
    }

    private Conta getContaCpf(String cpf) {
        return contas.stream().filter(c -> c.getCpf().equals(cpf)).findFirst().orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não enocntrada")
        );
    }
}
