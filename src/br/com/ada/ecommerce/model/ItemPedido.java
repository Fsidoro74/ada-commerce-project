package br.com.ada.ecommerce.model;

import java.util.Objects;

public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private double precoVenda;

    public ItemPedido(Produto produto, int quantidade, double precoVenda) {
        setProduto(produto);
        setQuantidade(quantidade);
        setPrecoVenda(precoVenda);
    }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoVenda() { return precoVenda; }

    public void setProduto(Produto produto) {
        if (produto == null) throw new IllegalArgumentException("Produto não pode ser nulo.");
        this.produto = produto;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        this.quantidade = quantidade;
    }

    public void setPrecoVenda(double precoVenda) {
        if (precoVenda <= 0) throw new IllegalArgumentException("Preço de venda deve ser maior que zero.");
        this.precoVenda = precoVenda;
    }

    public double calcularSubtotal() {
        return precoVenda * quantidade;
    }

    public String gerarResumo() {
        return String.format("%s x%d = R$ %.2f", produto.getNome(), quantidade, calcularSubtotal());
    }

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