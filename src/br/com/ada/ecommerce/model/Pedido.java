package br.com.ada.ecommerce.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Pedido {
    private static final AtomicLong ID_COUNTER = new AtomicLong();

    private final long id;
    private final Cliente cliente;
    private final List<ItemVenda> itens = new ArrayList<>();
    private StatusPedido status = StatusPedido.ABERTO;

    public Pedido(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente do pedido não pode ser nulo.");
        }
        this.id = ID_COUNTER.incrementAndGet();
        this.cliente = cliente;
    }

    public long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        if (status == null) {
            throw new IllegalArgumentException("O status do pedido não pode ser nulo.");
        }
        this.status = status;
    }

    public List<ItemVenda> getItens() {
        return Collections.unmodifiableList(itens);
    }

    /**
     * Adiciona um produto ao pedido; se já existir, soma a quantidade.
     */
    public void adicionarProduto(Produto produto, int quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        for (ItemVenda item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                return;
            }
        }
        itens.add(new ItemVenda(produto, quantidade, produto.getPrecoVenda()));
    }

    /**
     * Calcula o total do pedido com base nos itens.
     */
    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(i -> i.getPrecoVenda() * i.getQuantidade())
                .sum();
    }

    /**
     * Exibe um resumo do pedido no console.
     */
    public void exibirResumo() {
        System.out.println("\n--- Resumo do Pedido #" + id + " ---");
        System.out.println("Cliente: " + cliente.getNome());
        if (itens.isEmpty()) {
            System.out.println("  Nenhum item no pedido.");
        } else {
            for (ItemVenda item : itens) {
                System.out.println("  - " + item.getProduto().getNome() + " (x" + item.getQuantidade() + ") - R$ "
                        + String.format("%.2f", item.getPrecoVenda()) + " = R$ "
                        + String.format("%.2f", (item.getPrecoVenda() * item.getQuantidade())));
            }
        }
        System.out.println("Total do Pedido: R$ " + String.format("%.2f", calcularTotal()));
        System.out.println("Status: " + status);
        System.out.println("----------------------------");
    }
}

