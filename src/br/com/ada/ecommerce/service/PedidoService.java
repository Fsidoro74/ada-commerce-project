package br.com.ada.ecommerce.service;

import br.com.ada.ecommerce.model.Cliente;
import br.com.ada.ecommerce.model.ItemPedido;
import br.com.ada.ecommerce.model.Pedido;
import br.com.ada.ecommerce.model.Produto;
import br.com.ada.ecommerce.model.StatusPedido;
import br.com.ada.ecommerce.notificacao.Notificador;

import java.util.ArrayList;
import java.util.List;

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
    }

    /**
     * Retorna o resumo do pedido como String.
     */
    public String gerarResumoPedido(Pedido pedido) {
        return pedido.gerarResumo();
    }

    /**
     * Devolve os produtos de um pedido ao estoque.
     */
    public List<String> devolverProdutosAoEstoque(Pedido pedido) {
        List<String> logEstoque = new ArrayList<>();
        for (ItemPedido item : pedido.getItens()) {
            Produto produto = item.getProduto();
            int quantidade = item.getQuantidade();
            produto.incrementarEstoque(quantidade);
            logEstoque.add(String.format("Produto '%s' devolvido ao estoque. Estoque atual: %d",
                    produto.getNome(), produto.getQuantidade()));
        }
        return logEstoque;
    }

    /**
     * Finaliza um pedido, alterando seu status e notificando o cliente.
     */
    public void finalizarPedido(Pedido pedido) {
        if (!pedido.podeFinalizar()) {
            throw new IllegalArgumentException("Um pedido não pode ser finalizado sem itens ou com valor zero.");
        }
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        notificador.notificar(pedido.getCliente(),
                String.format("Seu pedido #%d está aguardando pagamento.", pedido.getId()));
    }

    /**
     * Realiza o pagamento de um pedido, alterando seu status e notificando o cliente.
     */
    public void pagar(Pedido pedido) {
        if (!pedido.getStatus().podeSerPago()) {
            throw new IllegalStateException(String.format(
                    "O pedido #%d não pode ser pago neste momento. Status atual: %s",
                    pedido.getId(), pedido.getStatus().getDescricao()));
        }
        pedido.setStatus(StatusPedido.PAGO);
        notificador.notificar(pedido.getCliente(),
                String.format("Seu pagamento do pedido #%d foi confirmado com sucesso.", pedido.getId()));
    }

    /**
     * Realiza a entrega de um pedido, alterando seu status e notificando o cliente.
     */
    public void entregar(Pedido pedido) {
        if (!pedido.getStatus().podeSerEntregue()) {
            throw new IllegalStateException(String.format(
                    "O pedido #%d não pode ser entregue. Primeiro ele precisa ser pago. Status atual: %s",
                    pedido.getId(), pedido.getStatus().getDescricao()));
        }
        pedido.setStatus(StatusPedido.FINALIZADO);
        notificador.notificar(pedido.getCliente(),
                String.format("Seu pedido #%d foi entregue com sucesso!", pedido.getId()));
    }
}