package br.com.ada.ecommerce.model;

/**
 * Classe que representa um produto no sistema de e-commerce.
 */
public class Produto {
<<<<<<< HEAD
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
=======
    private final int id;
    private final String nome;
    private double preco;
    private int quantidade;

    /**
     * Construtor do Produto
     * @param id Identificador único do produto
     * @param nome Nome do produto
     * @param preco Preço do produto
     * @param quantidade Quantidade em estoque
     * @throws IllegalArgumentException se o preço ou quantidade forem negativos
     */
    public Produto(int id, String nome, double preco, int quantidade) {
        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo");
        }
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa");
        }

        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getId() { 
        return id; 
    }

    public String getNome() { 
        return nome; 
    }

    public double getPreco() { 
        return preco; 
    }

    /**
     * Atualiza o preço do produto
     * @param preco Novo preço do produto
     * @throws IllegalArgumentException se o preço for negativo
     */
    public void setPreco(double preco) { 
        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo");
        }
        this.preco = preco; 
    }

    public int getQuantidade() { 
        return quantidade; 
    }

    /**
     * Atualiza a quantidade em estoque do produto
     * @param quantidade Nova quantidade em estoque
     * @throws IllegalArgumentException se a quantidade for negativa
     */
    public void setQuantidade(int quantidade) { 
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa");
        }
        this.quantidade = quantidade; 
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
    }
}