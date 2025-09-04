package br.com.ada.ecommerce.service;

import br.com.ada.ecommerce.model.*;

public class PedidoService {

    public void adicionarItemAoPedido(Pedido pedido, Produto produto, int quantidade, double precoVenda) {
        ItemPedido item = new ItemPedido(produto, quantidade, precoVenda);
        pedido.adicionarItem(item);
    }

    public void exibirResumoPedido(Pedido pedido) {
        pedido.exibirResumo();
    }

    public void finalizarPedido(Pedido pedido) {
        if (pedido.podeFinalizar()) {
            pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
            System.out.println("✅ Pedido finalizado com sucesso!");
            enviarEmail(pedido.getCliente(), "Seu pedido foi finalizado e está aguardando pagamento.");
        } else {
            System.out.println("⚠️ Pedido não pode ser finalizado. Verifique se há itens e valor maior que zero.");
        }
    }

    public void realizarPagamento(Pedido pedido) {
        if (pedido.getStatus() == StatusPedido.AGUARDANDO_PAGAMENTO) {
            pedido.setStatus(StatusPedido.PAGO);
            System.out.println("💳 Pagamento realizado com sucesso!");
            enviarEmail(pedido.getCliente(), "Pagamento confirmado! Seu pedido será entregue em breve.");
        } else {
            System.out.println("❌ Pagamento não permitido. O pedido não está aguardando pagamento.");
        }
    }

    public void realizarEntrega(Pedido pedido) {
        if (pedido.getStatus() == StatusPedido.PAGO) {
            pedido.setStatus(StatusPedido.FINALIZADO);
            System.out.println("📦 Pedido entregue com sucesso!");
            enviarEmail(pedido.getCliente(), "Seu pedido foi entregue. Obrigado por comprar com a Ada Commerce!");
        } else {
            System.out.println("❌ Entrega não permitida. O pedido ainda não foi pago.");
        }
    }

    private void enviarEmail(Cliente cliente, String mensagem) {
        System.out.println("📧 Enviando e-mail para " + cliente.getNome() + ": " + mensagem);
    }
}
