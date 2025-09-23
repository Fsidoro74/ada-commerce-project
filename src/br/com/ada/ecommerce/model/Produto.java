package br.com.ada.ecommerce.model;

import java.util.Objects;

public class Produto {

    private long id;
    private String nome;
    private double preco;
    private int quantidade; // estoque disponível

    public Produto(long id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Operações de estoque
    public void decrementarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade inválida para remoção.");
        }
        if (this.quantidade < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente para o produto: " + nome);
        }
        this.quantidade -= quantidade;
    }

    public void incrementarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade inválida para incremento.");
        }
        this.quantidade += quantidade;
    }

    // Método digital: retorna resumo do produto
    public String gerarResumo() {
        return String.format("Produto #%d - %s | Preço: R$ %.2f | Estoque: %d",
                id, nome, preco, quantidade);
    }

    // Equals e HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return id == produto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}