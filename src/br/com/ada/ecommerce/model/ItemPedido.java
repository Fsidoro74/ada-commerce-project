package br.com.ada.ecommerce.model;

public class ItemPedido {
    private final Produto produto;
    private int quantidade;
    private double precoVenda; // valor de venda informado no pedido

    public ItemPedido(Produto produto, int quantidade, double precoVenda) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        if (precoVenda < 0) throw new IllegalArgumentException("Preço de venda não pode ser negativo");
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoVenda() { return precoVenda; }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        this.quantidade = quantidade;
    }

    public void setPrecoVenda(double precoVenda) {
        if (precoVenda < 0) throw new IllegalArgumentException("Preço de venda não pode ser negativo");
        this.precoVenda = precoVenda;
    }

    public double getSubtotal() {
        return quantidade * precoVenda;
    }
}
