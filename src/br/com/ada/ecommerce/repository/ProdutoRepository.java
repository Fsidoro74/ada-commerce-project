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
        // Em um sistema real, você verificaria se o produto já existe por ID
        // Para este exercício em memória, simplesmente adicionamos.
        this.produtos.add(produto);
        System.out.println("Produto " + produto.getNome() + " salvo no repositório.");
    }

    /**
     * Lista todos os produtos atualmente no repositório.
     * @return Uma lista imutável de todos os produtos.
     */
    public List<Produto> listarTodos() {
        return Collections.unmodifiableList(produtos);
    }

    /**
     * Busca um produto pelo seu ID.
     * @param id O ID do produto a ser buscado.
     * @return Um Optional contendo o produto, se encontrado; Optional.empty() caso contrário.
     */
    public Optional<Produto> buscarPorId(long id) {
        return produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    // Você pode adicionar outros métodos de busca, como buscarPorNome, se necessário.
}


