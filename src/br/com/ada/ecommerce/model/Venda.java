package br.com.ada.ecommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Venda {

    private Long id;
    private Cliente cliente;
    private LocalDateTime dataVenda;
    private StatusPedido status;
    private List<ItemVenda> itens = new ArrayList<>();
    private double valorTotal;

    public Venda() {
        this.dataVenda = LocalDateTime.now();
        this.status = StatusPedido.ABERTO;
    }

    public Venda(Cliente cliente) {
        this.cliente = cliente;
        this.dataVenda = LocalDateTime.now();
        this.status = StatusPedido.ABERTO;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItemVenda> getItens() {
        return new ArrayList<>(itens);
    }

    public double getValorTotal() {
        return valorTotal;
    }

    // Operações
    public void adicionarItem(ItemVenda item) {
        this.itens.add(item);
        calcularValorTotal();
    }

    public void removerItem(ItemVenda item) {
        this.itens.remove(item);
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        this.valorTotal = itens.stream()
                .mapToDouble(item -> item.getPrecoVenda() * item.getQuantidade())
                .sum();
    }

    public boolean podeFinalizar() {
        return !itens.isEmpty() && valorTotal > 0;
    }

    // Método digital: retorna resumo da venda
    public String gerarResumo() {
        StringBuilder resumo = new StringBuilder();
        resumo.append("Venda #").append(id != null ? id : "N/A").append("\n");
        resumo.append("Cliente: ").append(cliente != null ? cliente.getNome() : "Desconhecido").append("\n");
        resumo.append("Data: ").append(dataVenda).append("\n");
        resumo.append("Status: ").append(status.name()).append("\n");
        resumo.append("Itens:\n");

        for (ItemVenda item : itens) {
            resumo.append("- ")
                    .append(item.getProduto().getNome())
                    .append(" x").append(item.getQuantidade())
                    .append(" = R$ ").append(String.format("%.2f", item.getPrecoVenda() * item.getQuantidade()))
                    .append("\n");
        }

        resumo.append("Valor Total: R$ ").append(String.format("%.2f", valorTotal));
        return resumo.toString();
    }

    // Equals e HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venda)) return false;
        Venda venda = (Venda) o;
        return Objects.equals(id, venda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}