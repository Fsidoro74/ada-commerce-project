package br.com.ada.ecommerce.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pedido {

    private Long id;
    private Cliente cliente;
    private LocalDate dataCriacao;
    private StatusPedido status;
    private List<ItemPedido> itens = new ArrayList<>();
    private double desconto = 0.0;

    public Pedido(Long id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.dataCriacao = LocalDate.now();
        this.status = StatusPedido.ABERTO;
    }

    // Getters e Setters
    public Long getId() {
        return id;
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
        return new ArrayList<>(itens);
    }

    public double getDesconto() {
        return desconto;
    }

    public void aplicarDesconto(double valor) {
        this.desconto = valor;
    }

    // Operações
    public void adicionarItem(ItemPedido item) {
        if (status != StatusPedido.ABERTO) {
            throw new IllegalStateException("Itens só podem ser adicionados em pedidos abertos.");
        }
        itens.add(item);
    }

    public void removerItem(ItemPedido item) {
        if (status != StatusPedido.ABERTO) {
            throw new IllegalStateException("Itens só podem ser removidos em pedidos abertos.");
        }
        itens.remove(item);
    }

    public void alterarQuantidade(ItemPedido item, int novaQuantidade) {
        if (status != StatusPedido.ABERTO) {
            throw new IllegalStateException("Quantidade só pode ser alterada em pedidos abertos.");
        }
        item.setQuantidade(novaQuantidade);
    }

    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(ItemPedido::calcularSubtotal)
                .sum();
    }

    public double getValorTotal() {
        return calcularTotal() - desconto;
    }

    public boolean podeFinalizar() {
        return !itens.isEmpty() && getValorTotal() > 0;
    }

    public String gerarResumo() {
        StringBuilder resumo = new StringBuilder();
        resumo.append("🧾 Resumo do Pedido #").append(id).append("\n");
        resumo.append("Cliente: ").append(cliente.getNome()).append("\n");
        resumo.append("Data: ").append(dataCriacao).append("\n");

        for (ItemPedido item : itens) {
            resumo.append("- ").append(item.gerarResumo()).append("\n");
        }

        resumo.append("Subtotal: R$ ").append(String.format("%.2f", calcularTotal())).append("\n");
        resumo.append("Desconto: R$ ").append(String.format("%.2f", desconto)).append("\n");
        resumo.append("Total: R$ ").append(String.format("%.2f", getValorTotal())).append("\n");
        resumo.append("Status: ").append(status.name());

        return resumo.toString();
    }

    // Equals e HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}