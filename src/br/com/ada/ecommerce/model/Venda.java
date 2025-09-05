<<<<<<< HEAD
// ...
// ...import br.c
import br.com.ada.ecommerce.model.StatusPedido;

public class Venda {
    private StatusPedido status;
    // ...

    public Venda() {
        this.status = StatusPedido.ABERTO;
    }

    // Método para obter o status
=======
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

>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
    public StatusPedido getStatus() {
        return status;
    }

<<<<<<< HEAD
    // Método para alterar o status
    public void setStatus(StatusPedido novoStatus) {
        this.status = novoStatus;
=======
    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItemVenda> getItens() {
        return new ArrayList<>(itens);
    }

    public void adicionarItem(ItemVenda item) {
        this.itens.add(item);
        calcularValorTotal();
    }

    public void removerItem(ItemVenda item) {
        this.itens.remove(item);
        calcularValorTotal();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    private void calcularValorTotal() {
        this.valorTotal = itens.stream()
                .mapToDouble(item -> item.getPrecoUnitario() * item.getQuantidade())
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return Objects.equals(id, venda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", dataVenda=" + dataVenda +
                ", status=" + status +
                ", valorTotal=" + valorTotal +
                '}';
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
    }
}
