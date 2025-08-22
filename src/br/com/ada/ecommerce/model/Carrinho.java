package br.com.ada.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Produto> itens = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        itens.add(produto);
    }

    public void exibirCarrinho() {
        if (itens.isEmpty()) {
            System.out.println("Carrinho vazio.");
            return;
        }

        System.out.println("Itens no carrinho:");
        for (Produto p : itens) {
            System.out.println(p);
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (Produto p : itens) {
            total += p.getPreco();
        }
        return total;
    }

    public void limparCarrinho() {
        itens.clear();
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }
}

