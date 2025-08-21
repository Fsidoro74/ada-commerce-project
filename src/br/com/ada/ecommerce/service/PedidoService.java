package br.com.ada.ecommerce.service;

import br.com.ada.ecommerce.model.Pedido;
import br.com.ada.ecommerce.model.Produto;

public class PedidoService {
    public void adicionarProdutoAoPedido(Pedido pedido, Produto produto) {
        pedido.adicionarProduto(produto);
    }

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


