package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    private final List<Produto> produtos = new ArrayList<>();
    private Long proximoId = 1L;

    public void salvar(Produto produto) {
        produto.setId(proximoId++);
        produtos.add(produto);
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos);
    }

    public Produto buscarPorNome(String nome) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        return null;
    }

    public void atualizar(Produto produtoAtualizado) {
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            if (produto.getId().equals(produtoAtualizado.getId())) {
                produtos.set(i, produtoAtualizado);
                return;
            }
        }
    }
}