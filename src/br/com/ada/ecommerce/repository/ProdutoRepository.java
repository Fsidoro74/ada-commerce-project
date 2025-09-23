package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Produto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProdutoRepository {

    private final List<Produto> produtos = new ArrayList<>();

    /**
     * Salva um novo produto na lista.
     * @param produto O produto a ser salvo.
     */
    public void salvar(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        produtos.add(produto);
    }

    /**
     * Retorna todos os produtos cadastrados no repositório.
     * @return Uma lista imutável de produtos.
     */
    public List<Produto> listarTodos() {
        return Collections.unmodifiableList(new ArrayList<>(produtos));
    }

    /**
     * Busca um produto pelo seu ID.
     * @param id O ID do produto a ser buscado.
     * @return Um Optional contendo o produto, se encontrado.
     */
    public Optional<Produto> buscarPorId(long id) {
        return produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    /**
     * Busca um produto pelo nome.
     * @param nome Nome do produto.
     * @return Um Optional contendo o produto, se encontrado.
     */
    public Optional<Produto> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return Optional.empty();
        }

        return produtos.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome.trim()))
                .findFirst();
    }
}