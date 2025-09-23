package br.com.ada.ecommerce.model;

import java.util.Objects;

public class ItemPedido {

    private Produto produto;
    private int quantidade;
    private double precoVenda;

    public ItemPedido(Produto produto, int quantidade, double precoVenda) {
        validarParametros(produto, quantidade, precoVenda);
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    // Getters
    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    // Setters com validação
    public void setProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        this.produto = produto;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        this.quantidade = quantidade;
    }

    public void setPrecoVenda(double precoVenda) {
        if (precoVenda <= 0) {
            throw new IllegalArgumentException("Preço de venda deve ser maior que zero.");
        }
        this.precoVenda = precoVenda;
    }

    // Cálculo de subtotal
    public double calcularSubtotal() {
        return precoVenda * quantidade;
    }

    // Método digital: retorna resumo do item
    public String gerarResumo() {
        return String.format("%s x%d = R$ %.2f",
                produto.getNome(), quantidade, calcularSubtotal());
    }

    // Validação centralizada
    private void validarParametros(Produto produto, int quantidade, double precoVenda) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        if (precoVenda <= 0) {
            throw new IllegalArgumentException("Preço de venda deve ser maior que zero.");
        }
    }

    // Equals e HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedido)) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto);
    }
}