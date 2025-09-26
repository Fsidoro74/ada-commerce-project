package br.com.ada.ecommerce.model;

import java.util.Objects;

public class Produto {
    private Long id;
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(Long id, String nome, double preco, int quantidade) {
        setNome(nome);
        setPreco(preco);
        setQuantidade(quantidade);
        this.id = id;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome obrigatório.");
        this.nome = nome;
    }
    public double getPreco() { return preco; }
    public void setPreco(double preco) {
        if (preco <= 0) throw new IllegalArgumentException("Preço deve ser maior que zero.");
        this.preco = preco;
    }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) {
        if (quantidade < 0) throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        this.quantidade = quantidade;
    }

    public void decrementarEstoque(int valor) {
        if (valor > quantidade) throw new IllegalArgumentException("Estoque insuficiente.");
        this.quantidade -= valor;
    }

    public void incrementarEstoque(int valor) {
        if (valor <= 0) throw new IllegalArgumentException("Valor de incremento deve ser positivo.");
        this.quantidade += valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(nome, produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}