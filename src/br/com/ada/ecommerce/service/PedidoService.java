package br.com.ada.ecommerce.service;

import br.com.ada.ecommerce.model.Cliente;
import br.com.ada.ecommerce.model.ItemVenda; // Importe ItemVenda
import br.com.ada.ecommerce.model.*;
import br.com.ada.ecommerce.model.StatusPedido;
import br.com.ada.ecommerce.notificacao.Notificador;

import java.util.Map; // Mantenha, mas o loop será sobre List<ItemVenda>

public class PedidoService {
    public void adicionarProdutoAoPedido(Pedido pedido, Produto produto) {
        pedido.adicionarProduto(produto);
    }

    /**
     * Exibe um resumo do pedido.
     * @param pedido O pedido a ser exibido.
     */
    public void exibirResumoPedido(Pedido pedido) {
        double total = 0;
        System.out.println("Cliente: " + pedido.getCliente().getNome());
        for (Produto produto : pedido.getProdutos()) {
            System.out.println("- " + produto.getNome() + ": R$ " + produto.getPreco());
            total += produto.getPreco();
        }
        System.out.println("Total: R$ " + total);
    }
}


