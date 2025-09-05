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
        this.produtos.add(produto);
        System.out.println("Produto " + produto.getNome() + " salvo no repositório.");
    }

    /**
     * Retorna todos os produtos cadastrados no repositório.
     * @return Uma lista imutável de produtos.
     */
    public List<Produto> listarTodos() {
        return Collections.unmodifiableList(produtos);
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
        return produtos.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }
}

