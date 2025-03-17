package br.com.fiap.bankGestor.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.bankGestor.model.Conta;
import br.com.fiap.bankGestor.model.Transação;

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
        else if (conta.getSaldo() < 0) {
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

    @PutMapping("/conta/deposito")
    public <T> ResponseEntity<Object> deposito(@RequestBody Transação deposito){
        log.info("Depositando na conta: " + deposito.getId()); 
        var contaToUpdate = getContaNumero(deposito.getId());
        if (deposito.getValor() <= 0) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Valor do depósito deve ser maior que 0.");
            return (ResponseEntity<Object>) ResponseEntity.badRequest().body((T) errorResponse);
        }
        contas.remove(contaToUpdate);
        contaToUpdate.setSaldo(contaToUpdate.getSaldo() + deposito.getValor());
        contas.add(contaToUpdate);
        Map<String, Object> response = new HashMap<>();
        response.put("conta", contaToUpdate);
        response.put("deposito", deposito);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/conta/saque")
    public <T> ResponseEntity<Object> saque(@RequestBody Transação saque){
        log.info("Saque na conta: " + saque.getId()); 
        var contaToUpdate = getContaNumero(saque.getId());
        if (saque.getValor() <= 0) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Valor do saque deve ser maior que 0.");
            return (ResponseEntity<Object>) ResponseEntity.badRequest().body((T) errorResponse);
        }
        contas.remove(contaToUpdate);
        contaToUpdate.setSaldo(contaToUpdate.getSaldo() - saque.getValor());
        contas.add(contaToUpdate);
        Map<String, Object> response = new HashMap<>();
        response.put("conta", contaToUpdate);
        response.put("saque", saque);

        return ResponseEntity.ok(response);
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
