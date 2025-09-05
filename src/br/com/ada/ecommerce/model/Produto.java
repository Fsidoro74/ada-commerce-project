package br.com.ada.ecommerce.model;

/**
 * Classe que representa um produto no sistema de e-commerce.
 */
public class Produto {
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
    }
}