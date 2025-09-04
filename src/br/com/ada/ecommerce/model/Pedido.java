package br.com.ada.ecommerce.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    private static int SEQUENCE = 1;

    private final int id;
    private final Cliente cliente;
    private final List<ItemVenda> itens = new ArrayList<>();
    private StatusPedido status;

    public Pedido(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente do pedido não pode ser nulo.");
        }
        this.id = SEQUENCE++;
        this.cliente = cliente;
        this.status = StatusPedido.ABERTO;
    }

    public int getId() {
        return id;
    }

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
}

