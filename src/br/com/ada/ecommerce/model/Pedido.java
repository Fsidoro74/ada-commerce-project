package br.com.ada.ecommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Pedido {

    private static final AtomicLong ID_COUNTER = new AtomicLong();

    private final long id;
    private final Cliente cliente;
    private final List<ItemVenda> itens;
    private final LocalDateTime dataCriacao;
    private StatusPedido status;

    public Pedido(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O pedido precisa estar associado a um cliente.");
        }
        this.id = ID_COUNTER.incrementAndGet();
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusPedido.ABERTO;
    }

    public void adicionarProduto(Produto produto, int quantidade, double precoVenda) {
        if (status != StatusPedido.ABERTO) {
            throw new IllegalStateException("Só é possível adicionar produtos enquanto o pedido está ABERTO.");
        }

        for (ItemVenda item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                return;
            }
        }

        itens.add(new ItemVenda(produto, quantidade, precoVenda));
    }

    public void removerProduto(Produto produto) {
        if (status != StatusPedido.ABERTO) {
            throw new IllegalStateException("Só é possível remover produtos enquanto o pedido está ABERTO.");
        }

        boolean removido = itens.removeIf(item -> item.getProduto().getId() == produto.getId());
        if (!removido) {
            throw new IllegalArgumentException("Produto não encontrado no pedido.");
        }
    }

    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(item -> item.getPrecoVenda() * item.getQuantidade())
                .sum();
    }

    public void exibirResumo() {
        System.out.println("📦 Pedido #" + id);
        System.out.println("👤 Cliente: " + cliente.getNome());
        System.out.println("📅 Data: " + dataCriacao);
        System.out.println("📌 Status: " + status);

        if (itens.isEmpty()) {
            System.out.println("🛒 Nenhum item no pedido.");
        } else {
            for (ItemVenda item : itens) {
                System.out.printf("- %s x%d | R$ %.2f cada | Subtotal: R$ %.2f%n",
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getPrecoVenda(),
                        item.getQuantidade() * item.getPrecoVenda());
            }
            System.out.printf("💰 Total: R$ %.2f%n", calcularTotal());
        }
    }

    public boolean podeFinalizar() {
        return !itens.isEmpty() && calcularTotal() > 0;
    }

    // Getters
    public long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<ItemVenda> getItens() { return Collections.unmodifiableList(itens); }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
}