package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoRepository {

    private final List<Produto> produtos = new ArrayList<>();
    private Long proximoId = 1L;

    public void salvar(Produto produto) {
        if (buscarPorCodigo(produto.getCodigo()).isPresent()) {
            throw new IllegalArgumentException("Produto com este código já existe.");
        }
        produto.setId(proximoId++);
        produtos.add(produto);
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos);
    }

    public Optional<Produto> buscarPorNome(String nome) {
        return produtos.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public Optional<Produto> buscarPorCodigo(String codigo) {
        return produtos.stream()
                .filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    public void atualizar(Produto produtoAtualizado) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId().equals(produtoAtualizado.getId())) {
                produtos.set(i, produtoAtualizado);
                return;
            }
        }
        throw new IllegalArgumentException("Produto não encontrado para atualização.");
    }

    public void remover(Long id) {
        produtos.removeIf(p -> p.getId().equals(id));
    }
}