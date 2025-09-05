package br.com.ada.ecommerce.model;

public class ItemVenda {
    private Produto produto;
    private int quantidade;
    private double precoVenda; // Preço do produto no momento da venda, pode ser diferente do preço atual do produto

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

    // Não é comum ter um setPrecoVenda em ItemVenda após a criação,
    // pois o preço é fixado no momento da adição ao pedido.
    // Se precisar de alteração de preço (ex: promoções), a lógica deve ser mais complexa
    // e provavelmente na classe Carrinho antes da criação do ItemVenda.
}
