package br.com.ada.ecommerce.model;

public class ItemVenda {
    private Produto produto;
    private int quantidade;
    private double precoVenda; // Preço do produto no momento da venda

    public ItemVenda(Produto produto, int quantidade, double precoVenda) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade do item deve ser maior que zero.");
        }
        if (precoVenda <= 0) {
            throw new IllegalArgumentException("O preço de venda do item deve ser maior que zero.");
        }
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade do item deve ser maior que zero.");
        }
        this.quantidade = quantidade;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }
}
