package br.com.ada.ecommerce.model;

public class ItemPedido {
<<<<<<< HEAD
<<<<<<< HEAD
=======
    private Produto produto;
    private int quantidade;
    private double precoVenda;

    public ItemPedido(Produto produto, int quantidade, double precoVenda) {
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
    private final Produto produto;
    private int quantidade;
    private double precoVenda; // valor de venda informado no pedido

    public ItemPedido(Produto produto, int quantidade, double precoVenda) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        if (precoVenda < 0) throw new IllegalArgumentException("Preço de venda não pode ser negativo");
<<<<<<< HEAD
=======
    private Produto produto;
    private int quantidade;
    private double precoVenda;

    public ItemPedido(Produto produto, int quantidade, double precoVenda) {
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
=======
>>>>>>> 716dcb39db47a89030607c596407e4da9f215ebe
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoVenda() { return precoVenda; }

<<<<<<< HEAD
<<<<<<< HEAD
=======
    public double calcularSubtotal() {
        return quantidade * precoVenda;
    }

    @Override
    public String toString() {
        return produto.getNome() + " x" + quantidade + " (R$ " + precoVenda + " cada)";
    }
}
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
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
<<<<<<< HEAD
=======
    public double calcularSubtotal() {
        return quantidade * precoVenda;
    }

    @Override
    public String toString() {
        return produto.getNome() + " x" + quantidade + " (R$ " + precoVenda + " cada)";
    }
}
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
=======
>>>>>>> 716dcb39db47a89030607c596407e4da9f215ebe
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
