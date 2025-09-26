package br.com.ada.ecommerce.service;

import br.com.ada.ecommerce.model.*;
import br.com.ada.ecommerce.notificacao.Notificador;

public class PedidoService {

    private final Notificador notificador;

    public PedidoService(Notificador notificador) {
        this.notificador = notificador;
    }

    public void adicionarItemAoPedido(Pedido pedido, Produto produto, int quantidade, double precoVenda) {
        ItemPedido item = new ItemPedido(produto, quantidade, precoVenda);
        pedido.adicionarItem(item);
    }

    public void removerItem(Pedido pedido, Produto produto) {
        pedido.getItens().removeIf(item -> item.getProduto().equals(produto));
    }

    public void alterarQuantidade(Pedido pedido, Produto produto, int novaQuantidade) {
        for (ItemPedido item : pedido.getItens()) {
            if (item.getProduto().equals(produto)) {
                item.setQuantidade(novaQuantidade);
                return;
            }
        }
    }

    public void finalizarPedido(Pedido pedido) {
        if (!pedido.podeFinalizar()) {
            throw new IllegalArgumentException("Pedido precisa ter itens e valor maior que zero.");
        }
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        notificador.notificar(pedido.getCliente(), "Seu pedido #" + pedido.getId() + " está aguardando pagamento.");
    }

    public void pagarPedido(Pedido pedido) {
        if (pedido.getStatus() != StatusPedido.AGUARDANDO_PAGAMENTO) {
            throw new IllegalStateException("Pedido não está aguardando pagamento.");
        }
        pedido.setStatus(StatusPedido.PAGO);
        notificador.notificar(pedido.getCliente(), "Pagamento do pedido #" + pedido.getId() + " confirmado.");
    }

    public void entregarPedido(Pedido pedido) {
        if (pedido.getStatus() != StatusPedido.PAGO) {
            throw new IllegalStateException("Pedido precisa estar pago para ser entregue.");
        }
        pedido.setStatus(StatusPedido.FINALIZADO);
        notificador.notificar(pedido.getCliente(), "Pedido #" + pedido.getId() + " foi entregue com sucesso.");
    }

    public void aplicarCupom(Pedido pedido, Cupom cupom) {
        if (!cupom.estaValido()) {
            throw new IllegalArgumentException("Cupom inválido ou expirado.");
        }
        double desconto = cupom.aplicarDesconto(pedido.calcularTotal());
        pedido.aplicarDesconto(desconto);
        cupom.marcarComoUtilizado();
        notificador.notificar(pedido.getCliente(), "Cupom '" + cupom.getCodigo() + "' aplicado com sucesso.");
    }
}