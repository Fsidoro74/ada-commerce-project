package br.com.ada.ecommerce.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho {
    private List<ItemVenda> itens;

    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    /**
     * Adiciona um produto ao carrinho. Se o produto já existe, a quantidade é atualizada.
     * Decrementa o estoque do produto.
     */
    public void adicionarItem(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade a adicionar deve ser maior que zero.");
        }

        // Decrementa o estoque do produto ANTES de adicionar ao carrinho
        produto.decrementarEstoque(quantidade);

        for (ItemVenda item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                System.out.println("Quantidade do produto '" + produto.getNome() + "' atualizada no carrinho.");
                return;
            }
        }

        // Corrigido: produto.getPreco() em vez de getPrecoVenda()
        this.itens.add(new ItemVenda(produto, quantidade, produto.getPreco()));
        System.out.println("Produto '" + produto.getNome() + "' adicionado ao carrinho.");
    }

    public void removerItem(Produto produto) {
        ItemVenda itemRemover = null;
        for (ItemVenda item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                itemRemover = item;
                break;
            }
        }

        if (itemRemover != null) {
            produto.incrementarEstoque(itemRemover.getQuantidade());
            itens.remove(itemRemover);
            System.out.println("Produto '" + produto.getNome() + "' removido do carrinho. Estoque atual: " + produto.getQuantidade());
        } else {
            throw new IllegalArgumentException("Produto '" + produto.getNome() + "' não encontrado no carrinho.");
        }
    }

    public void alterarQuantidadeItem(Produto produto, int novaQuantidade) {
        if (novaQuantidade <= 0) {
            throw new IllegalArgumentException("A nova quantidade deve ser maior que zero. Para remover, use o método removerItem.");
        }

        for (ItemVenda item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                int diferencaQuantidade = novaQuantidade - item.getQuantidade();

                if (diferencaQuantidade > 0) {
                    produto.decrementarEstoque(diferencaQuantidade);
                } else if (diferencaQuantidade < 0) {
                    produto.incrementarEstoque(Math.abs(diferencaQuantidade));
                }

                item.setQuantidade(novaQuantidade);
                System.out.println("Quantidade do produto '" + produto.getNome() + "' alterada para " + novaQuantidade + ". Estoque atual: " + produto.getQuantidade());
                return;
            }
        }
        throw new IllegalArgumentException("Produto '" + produto.getNome() + "' não encontrado no carrinho.");
    }

    public double calcularTotal() {
        return itens.stream()
                    .mapToDouble(item -> item.getPrecoVenda() * item.getQuantidade())
                    .sum();
    }

    public void esvaziarCarrinho() {
        for (ItemVenda item : itens) {
            item.getProduto().incrementarEstoque(item.getQuantidade());
        }
        this.itens.clear();
        System.out.println("Carrinho esvaziado e produtos devolvidos ao estoque.");
    }

    public List<ItemVenda> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public void exibirCarrinho() {
        System.out.println("\n--- Conteúdo do Carrinho ---");
        if (itens.isEmpty()) {
            System.out.println("  Carrinho vazio.");
        } else {
            for (ItemVenda item : itens) {
                System.out.println("  - " + item.getProduto().getNome() +
                        " (x" + item.getQuantidade() + ") - R$ " +
                        String.format("%.2f", item.getPrecoVenda()) +
                        " = R$ " + String.format("%.2f", (item.getPrecoVenda() * item.getQuantidade())));
            }
        }
        System.out.println("Total do Carrinho: R$ " + String.format("%.2f", calcularTotal()));
        System.out.println("----------------------------");
    }
}