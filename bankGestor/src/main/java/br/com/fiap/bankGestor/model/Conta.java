package br.com.fiap.bankGestor.model;

import java.util.Random;

public class Conta {
    private Long numero;
    private String agencia;
    private String nome;
    private String cpf;
    private String dataDeAbertura;
    private Double saldoInicial;
    private Status status;
    private Tipo tipo;
    
    public Conta(Long numero, String agencia, String nome, String cpf, String dataDeAbertura, Double saldoInicial,
            Status status, Tipo tipo) {
        this.numero =Math.abs(new Random().nextLong());
        this.agencia = agencia;
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeAbertura = dataDeAbertura;
        this.saldoInicial = saldoInicial;
        this.status = status;
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
    public String getDataDeAbertura() {
        return dataDeAbertura;
    }
    public Double getSaldoInicial() {
        return saldoInicial;
    }
    public Status getStatus() {
        return status;
    }
    public Tipo getTipo() {
        return tipo;
    }

    
    
}
