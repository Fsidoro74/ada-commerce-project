package br.com.ada.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class HistoricoPedidos {

    private List<Pedido> pedidos;

    public HistoricoPedidos() {
        this.pedidos = new ArrayList<>();
    }

    // Adiciona pedido finalizado ao histórico
    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    // Exibe o histórico de todos os pedidos
    public void exibirHistorico() {
        if (pedidos.isEmpty()) {
            System.out.println("📭 Nenhum pedido realizado ainda.");
            return;
        }

        System.out.println("📚 Histórico de Pedidos:");
        int count = 1;

        for (Pedido pedido : pedidos) {
            System.out.println("\n🧾 Pedido #" + count + ":");
            pedido.exibirResumo();
            count++;
        }
    }

    // (Opcional) você pode querer acessar os pedidos individualmente
    public List<Pedido> getPedidos() {
        return pedidos;
    }
}

