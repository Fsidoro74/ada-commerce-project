package br.com.ada.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final Cliente cliente;
    private final List<Produto> produtos = new ArrayList<>();

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }
}

