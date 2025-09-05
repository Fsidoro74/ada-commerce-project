package br.com.ada.ecommerce.service;

<<<<<<< HEAD
import br.com.ada.ecommerce.model.Cliente;
import br.com.ada.ecommerce.model.ItemVenda; // Importe ItemVenda
import br.com.ada.ecommerce.model.Pedido;
import br.com.ada.ecommerce.model.Produto;
import br.com.ada.ecommerce.model.StatusPedido;
import br.com.ada.ecommerce.notificacao.Notificador;

import java.util.Map; // Mantenha, mas o loop será sobre List<ItemVenda>

public class PedidoService {

    private Notificador notificador;

    public PedidoService(Notificador notificador) {
        this.notificador = notificador;
=======
import br.com.ada.ecommerce.model.*;

public class PedidoService {

    public void adicionarItemAoPedido(Pedido pedido, Produto produto, int quantidade, double precoVenda) {
        ItemPedido item = new ItemPedido(produto, quantidade, precoVenda);
        pedido.adicionarItem(item);
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
    }

    /**
     * Adiciona um produto a um pedido, decrementando o estoque do produto.
     * @param pedido O pedido ao qual o produto será adicionado.
     * @param produto O produto a ser adicionado.
     * @param quantidade A quantidade do produto.
     * @throws IllegalArgumentException se o estoque for insuficiente (tratado pelo Produto).
     */
    public void adicionarProdutoAoPedido(Pedido pedido, Produto produto, int quantidade) {
        produto.decrementarEstoque(quantidade);
        pedido.adicionarProduto(produto, quantidade); // Lógica para adicionar ao pedido
        System.out.println("Produto " + produto.getNome() + " adicionado ao pedido. Estoque atual: " + produto.getQuantidade());
    }

    /**
     * Exibe um resumo do pedido.
     * @param pedido O pedido a ser exibido.
     */
    public void exibirResumoPedido(Pedido pedido) {
        pedido.exibirResumo();
    }

<<<<<<< HEAD
    /**
     * Devolve os produtos de um pedido ao estoque.
     * Utilizado, por exemplo, em caso de cancelamento.
     * @param pedido O pedido cujos produtos serão devolvidos.
     */
    public void devolverProdutosAoEstoque(Pedido pedido) {
        // CORREÇÃO AQUI: Iteramos sobre a List<ItemVenda>
        for (ItemVenda itemVenda : pedido.getItens()) {
            Produto produto = itemVenda.getProduto();
            int quantidade = itemVenda.getQuantidade();
            produto.incrementarEstoque(quantidade);
            System.out.println("Produto " + produto.getNome() + " devolvido ao estoque. Estoque atual: " + produto.getQuantidade());
        }
    }

    /**
     * Finaliza um pedido, alterando seu status e notificando o cliente.
     * @param pedido O pedido a ser finalizado.
     */
    public void finalizarPedido(Pedido pedido) {
        if (pedido.calcularTotal() <= 0 || pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("Um pedido não pode ser finalizado sem itens ou com valor zero.");
        }
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        notificador.notificar(pedido.getCliente(), "Seu pedido #" + pedido.getId() + " está aguardando pagamento.");
        System.out.println("Pedido #" + pedido.getId() + " finalizado. Status: " + pedido.getStatus());
    }

    /**
     * Realiza o pagamento de um pedido, alterando seu status e notificando o cliente.
     * @param pedido O pedido a ser pago.
     */
    public void pagar(Pedido pedido) {
        if (pedido.getStatus() != StatusPedido.AGUARDANDO_PAGAMENTO) {
            throw new IllegalStateException("O pedido #" + pedido.getId() + " não pode ser pago neste momento. Status atual: " + pedido.getStatus());
        }
        pedido.setStatus(StatusPedido.PAGO);
        notificador.notificar(pedido.getCliente(), "Seu pagamento do pedido #" + pedido.getId() + " foi confirmado com sucesso.");
        System.out.println("Pedido #" + pedido.getId() + " pago. Status: " + pedido.getStatus());
    }

    /**
     * Realiza a entrega de um pedido, alterando seu status e notificando o cliente.
     * @param pedido O pedido a ser entregue.
     */
    public void entregar(Pedido pedido) {
        if (pedido.getStatus() != StatusPedido.PAGO) {
            throw new IllegalStateException("O pedido #" + pedido.getId() + " não pode ser entregue. Primeiro ele precisa ser pago. Status atual: " + pedido.getStatus());
        }
        pedido.setStatus(StatusPedido.FINALIZADO);
        notificador.notificar(pedido.getCliente(), "Seu pedido #" + pedido.getId() + " foi entregue com sucesso!");
        System.out.println("Pedido #" + pedido.getId() + " entregue. Status: " + pedido.getStatus());
=======
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
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
    }
}
