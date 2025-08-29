package br.com.ada.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class HistoricoPedidos {

    private List<Pedido> pedidos = new ArrayList<>();

    public void adicionarPedido(Pedido pedido) {
        if (pedido.getStatus() != StatusPedido.FINALIZADO)
            throw new IllegalArgumentException("Só pedidos finalizados podem ser adicionados ao histórico.");
        pedidos.add(pedido);
    }

    public void exibirHistorico() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido finalizado no histórico.");
            return;
        }
        int count = 1;
        for (Pedido pedido : pedidos) {
            System.out.println("\nPedido #" + count++);
            pedido.exibirResumo();
        }
    }
}
