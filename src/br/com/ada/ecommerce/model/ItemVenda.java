package br.com.ada.ecommerce.model;

public class ItemVenda {

    private Produto produto;
    private int quantidade;
    private double precoVenda; // Preço do produto no momento da venda

    public ItemVenda(Produto produto, int quantidade, double precoVenda) {
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

    // Setter com validação
    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade do item deve ser maior que zero.");
        }
        this.quantidade = quantidade;
    }

    // Cálculo de subtotal
    public double calcularSubtotal() {
        return quantidade * precoVenda;
    }

    // Método digital: retorna resumo do item
    public String gerarResumo() {
        return String.format("%s x%d = R$ %.2f",
                produto.getNome(), quantidade, calcularSubtotal());
    }

    // Validação centralizada
    private void validarParametros(Produto produto, int quantidade, double precoVenda) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade do item deve ser maior que zero.");
        }
        if (precoVenda <= 0) {
            throw new IllegalArgumentException("O preço de venda do item deve ser maior que zero.");
        }
    }
}