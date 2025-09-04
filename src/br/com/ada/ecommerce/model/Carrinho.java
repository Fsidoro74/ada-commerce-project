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
     * @param produto O produto a ser adicionado.
     * @param quantidade A quantidade do produto.
     * @throws IllegalArgumentException se a quantidade for menor ou igual a zero ou se o estoque for insuficiente.
     */
    public void adicionarItem(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade a adicionar deve ser maior que zero.");
        }

        // Decrementa o estoque do produto ANTES de adicionar ao carrinho
        // Se o estoque for insuficiente, uma exceção será lançada aqui
        produto.decrementarEstoque(quantidade);

        for (ItemVenda item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                System.out.println("Quantidade do produto '" + produto.getNome() + "' atualizada no carrinho.");
                return;
            }
        }
        // Se o item não está no carrinho, adiciona um novo ItemVenda
        this.itens.add(new ItemVenda(produto, quantidade, produto.getPrecoVenda()));
        System.out.println("Produto '" + produto.getNome() + "' adicionado ao carrinho.");
    }

    /**
     * Remove um item do carrinho, incrementando o estoque do produto.
     * @param produto O produto a ser removido.
     * @throws IllegalArgumentException se o produto não for encontrado no carrinho.
     */
    public void removerItem(Produto produto) {
        ItemVenda itemRemover = null;
        for (ItemVenda item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                itemRemover = item;
                break;
            }
        }

        if (itemRemover != null) {
            // Incrementa o estoque do produto ao remover do carrinho
            produto.incrementarEstoque(itemRemover.getQuantidade());
            itens.remove(itemRemover);
            System.out.println("Produto '" + produto.getNome() + "' removido do carrinho. Estoque atual: " + produto.getQuantidade());
        } else {
            throw new IllegalArgumentException("Produto '" + produto.getNome() + "' não encontrado no carrinho.");
        }
    }

    /**
     * Altera a quantidade de um item no carrinho, ajustando o estoque do produto.
     * @param produto O produto cuja quantidade será alterada.
     * @param novaQuantidade A nova quantidade desejada para o item.
     * @throws IllegalArgumentException se a nova quantidade for menor ou igual a zero ou se o estoque for insuficiente.
     */
    public void alterarQuantidadeItem(Produto produto, int novaQuantidade) {
        if (novaQuantidade <= 0) {
            throw new IllegalArgumentException("A nova quantidade deve ser maior que zero. Para remover, use o método removerItem.");
        }

        for (ItemVenda item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                int diferencaQuantidade = novaQuantidade - item.getQuantidade();

                if (diferencaQuantidade > 0) { // Aumentando a quantidade
                    produto.decrementarEstoque(diferencaQuantidade);
                } else if (diferencaQuantidade < 0) { // Diminuindo a quantidade
                    produto.incrementarEstoque(Math.abs(diferencaQuantidade));
                }

                item.setQuantidade(novaQuantidade);
                System.out.println("Quantidade do produto '" + produto.getNome() + "' alterada para " + novaQuantidade + ". Estoque atual: " + produto.getQuantidade());
                return;
            }
        }
        throw new IllegalArgumentException("Produto '" + produto.getNome() + "' não encontrado no carrinho.");
    }

    /**
     * Calcula o valor total dos itens no carrinho.
     * @return O valor total.
     */
    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(item -> item.getPrecoVenda() * item.getQuantidade())
                .sum();
    }

    /**
     * Esvazia o carrinho, devolvendo todos os produtos para o estoque.
     */
    public void esvaziarCarrinho() {
        for (ItemVenda item : itens) {
            item.getProduto().incrementarEstoque(item.getQuantidade());
        }
        this.itens.clear();
        System.out.println("Carrinho esvaziado e produtos devolvidos ao estoque.");
    }

    /**
     * Retorna uma lista não modificável dos itens no carrinho.
     * @return Uma lista de ItemVenda.
     */
    public List<ItemVenda> getItens() {
        return Collections.unmodifiableList(itens);
    }

    /**
     * Exibe um resumo do conteúdo do carrinho no console.
     */
    public void exibirCarrinho() {
        System.out.println("\n--- Conteúdo do Carrinho ---");
        if (itens.isEmpty()) {
            System.out.println("  Carrinho vazio.");
        } else {
            for (ItemVenda item : itens) {
                System.out.println("  - " + item.getProduto().getNome() + " (x" + item.getQuantidade() + ") - R$ " + String.format("%.2f", item.getPrecoVenda()) + " = R$ " + String.format("%.2f", (item.getPrecoVenda() * item.getQuantidade())));
            }
        }
        System.out.println("Total do Carrinho: R$ " + String.format("%.2f", calcularTotal()));
        System.out.println("----------------------------");
    }
}