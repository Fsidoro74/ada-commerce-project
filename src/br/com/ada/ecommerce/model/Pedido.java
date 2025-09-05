package br.com.ada.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static long SEQ = 1;
    private final long id;
    private final Cliente cliente;
    private final List<Produto> produtos = new ArrayList<>();

    public Pedido(Cliente cliente) {
        this.id = SEQ++;
        this.cliente = cliente;
    }

    public long getId() { return id; }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public double calcularTotal() {
        return produtos.stream().mapToDouble(Produto::getPreco).sum();
    }

    public void exibirResumo() {
        System.out.println("👤 Cliente: " + cliente.getNome());

        if (produtos.isEmpty()) {
            System.out.println("🛒 Nenhum produto no pedido.");
            return;
        }

        System.out.println("📦 Produtos:");
        double total = 0;
        for (Produto produto : produtos) {
            System.out.println("- " + produto.getNome() + " (R$ " + produto.getPreco() + ")");
            total += produto.getPreco();
        }

        System.out.printf("💰 Total do Pedido: R$ %.2f%n", total);
    }

}


