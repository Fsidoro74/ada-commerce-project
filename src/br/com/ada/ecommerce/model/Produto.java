package br.com.ada.ecommerce.model;

public class Produto {
    private int id;
    private String nome;
    private double precoOriginal;
    private double precoVenda; // Pode ser diferente do original
    private int quantidade; // Este é o estoque

    public Produto(int id, String nome, double precoOriginal, double precoVenda, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.precoOriginal = precoOriginal;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoOriginal() {
        return precoOriginal;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    // ✅ Método adicional para compatibilidade com chamadas getPreco()
    public double getPreco() {
        return precoVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Decrementa a quantidade do produto em estoque.
     * Realiza validação para garantir que o estoque não se torne negativo.
     * @param quantidade A quantidade a ser removida do estoque.
     * @throws IllegalArgumentException se a quantidade for maior que o estoque disponível.
     */
    public void decrementarEstoque(int quantidade) {
        if (this.quantidade < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente para o produto: " + this.nome);
        }
        this.quantidade -= quantidade;
    }

    /**
     * Incrementa a quantidade do produto em estoque.
     * @param quantidade A quantidade a ser adicionada ao estoque.
     */
    public void incrementarEstoque(int quantidade) {
        this.quantidade += quantidade;
    }

    @Override
    public String toString() {
        return nome + " (R$ " + precoVenda + ") - Estoque: " + quantidade;
    }
}