package br.com.ada.ecommerce.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private final Cliente cliente;
    private final LocalDate dataCriacao;
    private StatusPedido status;
    private final List<ItemPedido> itens;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.dataCriacao = LocalDate.now();
        this.status = StatusPedido.ABERTO;
        this.itens = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

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