package br.com.ada.ecommerce.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
<<<<<<< HEAD
    private static long SEQ = 1;
    private final long id;
=======
    private static int SEQUENCE = 1;

    private final int id;
>>>>>>> 716dcb39db47a89030607c596407e4da9f215ebe
    private final Cliente cliente;
    private final List<ItemVenda> itens = new ArrayList<>();
    private StatusPedido status;

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
        this.status = StatusPedido.ABERTO;
    }

    public int getId() {
        return id;
    }

    public long getId() { return id; }

    public Cliente getCliente() {
        return cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

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


