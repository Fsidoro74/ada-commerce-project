package br.com.ada.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class HistoricoPedidos {

    private final List<Pedido> pedidos = new ArrayList<>();

    public void adicionarPedido(Pedido pedido) {
        if (pedido != null) {
            pedidos.add(pedido);
        }
    }

    public List<Pedido> listarPedidos() {
        return new ArrayList<>(pedidos);
    }

    public boolean estaVazio() {
        return pedidos.isEmpty();
    }

    // Método digital: retorna resumo de todos os pedidos como lista de strings
    public List<String> gerarResumoHistorico() {
        List<String> resumos = new ArrayList<>();
        int count = 1;
        for (Pedido pedido : pedidos) {
            String resumo = String.format("Pedido #%d\n%s", count++, pedido.gerarResumo());
            resumos.add(resumo);
        }
        return resumos;
    }
}