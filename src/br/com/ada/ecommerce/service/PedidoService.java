package br.com.ada.ecommerce.service;

import br.com.ada.ecommerce.model.Cliente;
import br.com.ada.ecommerce.model.ItemPedido;
import br.com.ada.ecommerce.model.Pedido;
import br.com.ada.ecommerce.model.Produto;
import br.com.ada.ecommerce.model.StatusPedido;
import br.com.ada.ecommerce.notificacao.Notificador;

public class PedidoService {

    private final Notificador notificador;

    public PedidoService(Notificador notificador) {
        this.notificador = notificador;
    }

    /**
     * Adiciona um item ao pedido com preço de venda.
     */
    public void adicionarItemAoPedido(Pedido pedido, Produto produto, int quantidade, double precoVenda) {
        ItemPedido item = new ItemPedido(produto, quantidade, precoVenda);
        pedido.adicionarItem(item);
    }

    /**
     * Adiciona um produto a um pedido, decrementando o estoque do produto.
     */
    public void adicionarProdutoAoPedido(Pedido pedido, Produto produto, int quantidade) {
        produto.decrementarEstoque(quantidade);
        pedido.adicionarProduto(produto, quantidade);
        System.out.println("Produto " + produto.getNome() + " adicionado ao pedido. Estoque atual: " + produto.getQuantidade());
    }

    /**
     * Exibe um resumo do pedido.
     */
    public void exibirResumoPedido(Pedido pedido) {
        pedido.exibirResumo();
    }

    /**
     * Devolve os produtos de um pedido ao estoque.
     */
    public void devolverProdutosAoEstoque(Pedido pedido) {
        for (ItemPedido item : pedido.getItens()) {
            Produto produto = item.getProduto();
            int quantidade = item.getQuantidade();
            produto.incrementarEstoque(quantidade);
            System.out.println("Produto " + produto.getNome() + " devolvido ao estoque. Estoque atual: " + produto.getQuantidade());
        }
    }

    /**
     * Finaliza um pedido, alterando seu status e notificando o cliente.
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
     */
    public void entregar(Pedido pedido) {
        if (pedido.getStatus() != StatusPedido.PAGO) {
            throw new IllegalStateException("O pedido #" + pedido.getId() + " não pode ser entregue. Primeiro ele precisa ser pago. Status atual: " + pedido.getStatus());
        }
        pedido.setStatus(StatusPedido.FINALIZADO);
        notificador.notificar(pedido.getCliente(), "Seu pedido #" + pedido.getId() + " foi entregue com sucesso!");
        System.out.println("Pedido #" + pedido.getId() + " entregue. Status: " + pedido.getStatus());
    }
}
