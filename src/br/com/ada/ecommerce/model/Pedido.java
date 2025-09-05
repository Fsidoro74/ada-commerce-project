package br.com.ada.ecommerce.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pedido {

    private Long id;
    private Cliente cliente;
    private List<ItemPedido> itens = new ArrayList<>();
    private StatusPedido status = StatusPedido.ABERTO;

    public Pedido(Long id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
    }

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

    public List<ItemPedido> getItens() {
        return new ArrayList<>(itens);
    }

    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        ItemPedido item = new ItemPedido(produto, quantidade, produto.getPreco());
        this.itens.add(item);
    }

    public void removerItem(ItemPedido item) {
        this.itens.remove(item);
    }

    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(ItemPedido::calcularSubtotal)
                .sum();
    }

    public boolean podeFinalizar() {
        return !itens.isEmpty() && calcularTotal() > 0;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public void exibirResumo() {
        System.out.println("Resumo do Pedido #" + id);
        System.out.println("Cliente: " + cliente.getNome());
        for (ItemPedido item : itens) {
            System.out.println("- " + item.getProduto().getNome() +
                    " x" + item.getQuantidade() +
                    " = R$ " + item.calcularSubtotal());
        }
        System.out.println("Total: R$ " + calcularTotal());
        System.out.println("Status: " + status);
    }

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
