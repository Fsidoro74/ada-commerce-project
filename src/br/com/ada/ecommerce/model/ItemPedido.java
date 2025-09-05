package br.com.ada.ecommerce.model;

public class ItemPedido {
<<<<<<< HEAD
    private final Produto produto;
    private int quantidade;
    private double precoVenda; // valor de venda informado no pedido

    public ItemPedido(Produto produto, int quantidade, double precoVenda) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        if (precoVenda < 0) throw new IllegalArgumentException("Preço de venda não pode ser negativo");
=======
    private Produto produto;
    private int quantidade;
    private double precoVenda;

    public ItemPedido(Produto produto, int quantidade, double precoVenda) {
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoVenda() { return precoVenda; }

<<<<<<< HEAD
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
