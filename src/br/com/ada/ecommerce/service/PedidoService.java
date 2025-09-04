package br.com.ada.ecommerce.service;

import br.com.ada.ecommerce.model.Pedido;
import br.com.ada.ecommerce.model.StatusPedido;
import br.com.ada.ecommerce.notificacao.Notificador;
import br.com.ada.ecommerce.model.Cliente;

public class PedidoService {

    private final Notificador notificador;

    public PedidoService(Notificador notificador) {
        this.notificador = notificador;
    }

    /**
     * Finaliza o pedido, alterando o status e notificando o cliente.
     */
    public void finalizarPedido(Pedido pedido) {
        if (!pedido.podeFinalizar()) {
            throw new IllegalArgumentException("O pedido não pode ser finalizado: está vazio ou com valor zero.");
        }
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        notificarCliente(pedido, "Seu pedido #" + pedido.getId() + " está aguardando pagamento.");
    }

    /**
     * Realiza o pagamento do pedido.
     */
    public void pagar(Pedido pedido) {
        if (pedido.getStatus() != StatusPedido.AGUARDANDO_PAGAMENTO) {
            throw new IllegalStateException("O pedido não está pronto para pagamento.");
        }
        pedido.setStatus(StatusPedido.PAGO);
        notificarCliente(pedido, "Pagamento do pedido #" + pedido.getId() + " confirmado.");
    }

    /**
     * Realiza a entrega do pedido.
     */
    public void entregar(Pedido pedido) {
        if (pedido.getStatus() != StatusPedido.PAGO) {
            throw new IllegalStateException("O pedido precisa estar pago antes da entrega.");
        }
        pedido.setStatus(StatusPedido.FINALIZADO);
        notificarCliente(pedido, "Seu pedido #" + pedido.getId() + " foi entregue com sucesso!");
    }

    /**
     * Envia uma notificação ao cliente.
     */
    private void notificarCliente(Pedido pedido, String mensagem) {
        Cliente cliente = pedido.getCliente();
        notificador.notificar(cliente, mensagem);
    }
}