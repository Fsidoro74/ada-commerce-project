package br.com.ada.ecommerce.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
<<<<<<< HEAD
<<<<<<< HEAD
=======
    private static long SEQ = 1;
    private final long id;
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
    private static int SEQUENCE = 1;

    private final int id;
>>>>>>> 716dcb39db47a89030607c596407e4da9f215ebe
    private final Cliente cliente;
    private final List<ItemVenda> itens = new ArrayList<>();
    private StatusPedido status;
<<<<<<< HEAD
=======

    private final Cliente cliente;
    private final LocalDate dataCriacao;
    private StatusPedido status;
    private final List<ItemPedido> itens;
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89

    public Pedido(Cliente cliente) {
<<<<<<< HEAD
        this.id = SEQ++;
=======
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente do pedido não pode ser nulo.");
        }
        this.id = SEQUENCE++;
>>>>>>> 716dcb39db47a89030607c596407e4da9f215ebe
        this.cliente = cliente;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
        this.status = StatusPedido.ABERTO;
    }

    public int getId() {
        return id;
<<<<<<< HEAD
=======
        this.dataCriacao = LocalDate.now();
        this.status = StatusPedido.ABERTO;
        this.itens = new ArrayList<>();
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
    }

    public long getId() { return id; }

    public Cliente getCliente() {
        return cliente;
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
    public StatusPedido getStatus() {
        return status;
    }

<<<<<<< HEAD
=======
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public StatusPedido getStatus() {
        return status;
    }

>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
    public void setStatus(StatusPedido status) {
        this.status = status;
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
    public List<ItemVenda> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        // Se já existir o produto no pedido, apenas acumula a quantidade
        for (ItemVenda item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                return;
            }
        }
        // Caso contrário, adiciona um novo item com o preço de venda atual do produto
        itens.add(new ItemVenda(produto, quantidade, produto.getPrecoVenda()));
    }

    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(iv -> iv.getPrecoVenda() * iv.getQuantidade())
                .sum();
    }

    public void exibirResumo() {
        System.out.println("\n--- Resumo do Pedido #" + id + " ---");
        if (itens.isEmpty()) {
            System.out.println("  Pedido sem itens.");
        } else {
            for (ItemVenda iv : itens) {
                System.out.println("  - " + iv.getProduto().getNome() + " (x" + iv.getQuantidade() + ") - R$ "
                        + String.format("%.2f", iv.getPrecoVenda()) + " = R$ "
                        + String.format("%.2f", (iv.getPrecoVenda() * iv.getQuantidade())));
            }
        }
        System.out.println("Total do Pedido: R$ " + String.format("%.2f", calcularTotal()));
        System.out.println("Status: " + status);
        System.out.println("---------------------------\n");
    }
<<<<<<< HEAD
}

=======
    public List<ItemPedido> getItens() {
        return itens;
    }

    public void adicionarItem(ItemPedido item) {
        if (status == StatusPedido.ABERTO) {
            itens.add(item);
        } else {
            System.out.println("❌ Não é possível adicionar itens. Pedido não está aberto.");
        }
    }

    public void removerItem(int indice) {
        if (status == StatusPedido.ABERTO && indice >= 0 && indice < itens.size()) {
            itens.remove(indice);
        } else {
            System.out.println("❌ Não é possível remover item. Verifique o status ou índice.");
        }
    }

    public void alterarQuantidade(int indice, int novaQuantidade) {
        if (status == StatusPedido.ABERTO && indice >= 0 && indice < itens.size()) {
            ItemPedido item = itens.get(indice);
            itens.set(indice, new ItemPedido(item.getProduto(), novaQuantidade, item.getPrecoVenda()));
        } else {
            System.out.println("❌ Não é possível alterar quantidade. Verifique o status ou índice.");
        }
    }
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89

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
<<<<<<< HEAD
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
=======


>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
