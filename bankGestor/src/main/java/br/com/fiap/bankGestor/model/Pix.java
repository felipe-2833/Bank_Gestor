package br.com.fiap.bankGestor.model;

public class Pix {
    private Long idOrigem;
    private Long idDestino;
    private Double valor;

    public Pix(Long idOrigem, Long idDestino,Double valor) {
        this.idOrigem = idOrigem;
        this.idDestino = idDestino;
        this.valor = valor;
    }

    public Long getIdOrigem() {
        return idOrigem;
    }

    public Long getIdDestino() {
        return idDestino;
    }

    public Double getValor() {
        return valor;
    }
}
