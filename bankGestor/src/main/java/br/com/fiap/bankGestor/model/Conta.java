package br.com.fiap.bankGestor.model;

import java.time.LocalDate;
import java.util.Random;

public class Conta {
    private Long numero;
    private String agencia;
    private String nome;
    private String cpf;
    private LocalDate dataDeAbertura;
    private Double saldo;
    private Boolean status;
    private String tipo;
    public Conta(Long numero, String agencia, String nome, String cpf, LocalDate dataDeAbertura, Double saldo,
            Boolean status, String tipo) {
        this.numero = Math.abs(new Random().nextLong());
        this.agencia = agencia;
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeAbertura = dataDeAbertura;
        this.saldo = saldo;
        this.status = true;
        this.tipo = tipo;
    }
    public Long getNumero() {
        return numero;
    }
    public String getAgencia() {
        return agencia;
    }
    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public LocalDate getDataDeAbertura() {
        return dataDeAbertura;
    }
    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    } 

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    } 

    public String getTipo() {
        return tipo;
    } 
    
}
