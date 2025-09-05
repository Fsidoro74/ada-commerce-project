<<<<<<< HEAD
package br.com.ada.ecommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
=======
// ...
// ...import br.c
import br.com.ada.ecommerce.model.StatusPedido;
>>>>>>> 716dcb39db47a89030607c596407e4da9f215ebe

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
    public StatusPedido getStatus() {
        return status;
    }

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
                .mapToDouble(item -> item.getPrecoVenda() * item.getQuantidade())
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
    }
}
