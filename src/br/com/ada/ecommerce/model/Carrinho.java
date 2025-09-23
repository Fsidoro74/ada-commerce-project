package br.com.ada.ecommerce.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho {

    private final List<ItemVenda> itens;

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

        produto.decrementarEstoque(quantidade);

        for (ItemVenda item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                return;
            }
        }

        this.itens.add(new ItemVenda(produto, quantidade, produto.getPreco()));
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
        } else {
            throw new IllegalArgumentException("Produto não encontrado no carrinho.");
        }
    }

    public void alterarQuantidadeItem(Produto produto, int novaQuantidade) {
        if (novaQuantidade <= 0) {
            throw new IllegalArgumentException("A nova quantidade deve ser maior que zero. Para remover, use o método removerItem.");
        }

        for (ItemVenda item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                int diferenca = novaQuantidade - item.getQuantidade();

                if (diferenca > 0) {
                    produto.decrementarEstoque(diferenca);
                } else if (diferenca < 0) {
                    produto.incrementarEstoque(Math.abs(diferenca));
                }

                item.setQuantidade(novaQuantidade);
                return;
            }
        }

        throw new IllegalArgumentException("Produto não encontrado no carrinho.");
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
    }

    public List<ItemVenda> getItens() {
        return Collections.unmodifiableList(itens);
    }

    // Método digital: retorna resumo do carrinho como String
    public String gerarResumoCarrinho() {
        StringBuilder resumo = new StringBuilder();
        resumo.append("--- Conteúdo do Carrinho ---\n");

        if (itens.isEmpty()) {
            resumo.append("Carrinho vazio.\n");
        } else {
            for (ItemVenda item : itens) {
                resumo.append(String.format("- %s (x%d) - R$ %.2f = R$ %.2f\n",
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getPrecoVenda(),
                        item.getPrecoVenda() * item.getQuantidade()));
            }
        }

        resumo.append(String.format("Total do Carrinho: R$ %.2f\n", calcularTotal()));
        resumo.append("----------------------------");

        return resumo.toString();
    }
}