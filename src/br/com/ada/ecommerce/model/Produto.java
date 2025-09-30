package br.com.ada.ecommerce.model;

import java.util.Objects;

public class Produto {
    private Long id;
    private String codigo;
    private String nome;
    private double preco;
    private int quantidade;

    // Construtor principal
    public Produto(Long id, String codigo, String nome, double preco, int quantidade) {
        setCodigo(codigo);
        setNome(nome);
        setPreco(preco);
        setQuantidade(quantidade);
        this.id = id;
    }

    // Construtor alternativo sem ID (útil para criação antes de salvar)
    public Produto(String codigo, String nome, double preco, int quantidade) {
        this(null, codigo, nome, preco, quantidade);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código obrigatório.");
        }
        this.codigo = codigo;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome obrigatório.");
        }
        this.nome = nome;
    }

    public double getPreco() { return preco; }
    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero.");
        }
        this.preco = preco;
    }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        }
        this.quantidade = quantidade;
    }

    public void decrementarEstoque(int valor) {
        if (valor > quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }
        this.quantidade -= valor;
    }

    public void incrementarEstoque(int valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de incremento deve ser positivo.");
        }
        this.quantidade += valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(codigo, produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}