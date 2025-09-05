package br.com.ada.ecommerce.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
<<<<<<< HEAD
    private static int SEQUENCE = 1;

    private final int id;
    private final Cliente cliente;
    private final List<ItemVenda> itens = new ArrayList<>();
    private StatusPedido status;
=======

    private final Cliente cliente;
    private final LocalDate dataCriacao;
    private StatusPedido status;
    private final List<ItemPedido> itens;
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae

    public Pedido(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente do pedido não pode ser nulo.");
        }
        this.id = SEQUENCE++;
        this.cliente = cliente;
<<<<<<< HEAD
        this.status = StatusPedido.ABERTO;
    }

    public int getId() {
        return id;
=======
        this.dataCriacao = LocalDate.now();
        this.status = StatusPedido.ABERTO;
        this.itens = new ArrayList<>();
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
    }

    public Cliente getCliente() {
        return cliente;
    }

<<<<<<< HEAD
    public StatusPedido getStatus() {
        return status;
    }

=======
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public StatusPedido getStatus() {
        return status;
    }

>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
    public void setStatus(StatusPedido status) {
        this.status = status;
    }

<<<<<<< HEAD
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

    public double calcularTotal() {
        return itens.stream().mapToDouble(ItemPedido::calcularSubtotal).sum();
    }

    public boolean podeFinalizar() {
        return !itens.isEmpty() && calcularTotal() > 0;
    }

    public void exibirResumo() {
        System.out.println("📅 Data do Pedido: " + dataCriacao);
        System.out.println("👤 Cliente: " + cliente.getNome());
        System.out.println("📌 Status: " + status);

        if (itens.isEmpty()) {
            System.out.println("🛒 Nenhum item no pedido.");
            return;
        }

        System.out.println("📦 Itens:");
        for (int i = 0; i < itens.size(); i++) {
            ItemPedido item = itens.get(i);
            System.out.printf("%d - %s | Quantidade: %d | Preço: R$ %.2f | Subtotal: R$ %.2f%n",
                    i, item.getProduto().getNome(), item.getQuantidade(), item.getPrecoVenda(), item.calcularSubtotal());
        }

        System.out.printf("💰 Total do Pedido: R$ %.2f%n", calcularTotal());
    }
}
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
