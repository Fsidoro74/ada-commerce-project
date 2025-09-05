package br.com.ada.ecommerce.model;

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
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
<<<<<<< HEAD
=======
import java.util.Objects;

public class ItemVenda {
    private Long id;
    private Produto produto;
    private int quantidade;
    private double precoUnitario;

    public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
    }

    public Produto getProduto() {
        return produto;
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
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
<<<<<<< HEAD
=======
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public double getSubTotal() {
        return precoUnitario * quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemVenda itemVenda = (ItemVenda) o;
        return Objects.equals(id, itemVenda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ItemVenda{" +
                "produto=" + produto.getNome() +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                ", subTotal=" + getSubTotal() +
                '}';
    }
}
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
