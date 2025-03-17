package br.com.fiap.bankGestor.model;

public class Transação {
    private Long id;
    private Double valor;

    public Transação(Long id, Double valor) {
        this.id = id;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }
    
}
