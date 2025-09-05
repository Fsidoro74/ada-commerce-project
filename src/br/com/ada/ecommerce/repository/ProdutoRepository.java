package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Produto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProdutoRepository {
<<<<<<< HEAD
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
=======
    public List<Produto> buscarTodos() {
        List<Produto> produtos = new ArrayList<>();
        // Notebooks e Computadores
        produtos.add(new Produto(1, "Notebook", 3500.00, 10));
        produtos.add(new Produto(2, "Celular", 2200.00, 15));
        produtos.add(new Produto(3, "Fone Bluetooth", 300.00, 30));
        produtos.add(new Produto(4, "Notebook Gamer", 8500.00, 5));
        produtos.add(new Produto(5, "PC Desktop Completo", 4200.00, 8));
        produtos.add(new Produto(6, "Monitor 24 polegadas", 800.00, 20));

        // Smartphones e Acessórios
        produtos.add(new Produto(7, "iPhone 15", 5999.00, 12));
        produtos.add(new Produto(8, "Samsung Galaxy S23", 4999.00, 15));
        produtos.add(new Produto(9, "Carregador Wireless", 150.00, 40));
        produtos.add(new Produto(10, "Capa Protetora", 50.00, 100));

        // Áudio e Som
        produtos.add(new Produto(11, "Caixa de Som Bluetooth", 250.00, 25));
        produtos.add(new Produto(12, "Fone de Ouvido Pro", 899.00, 18));
        produtos.add(new Produto(13, "Microfone Condensador", 350.00, 15));

        // Periféricos
        produtos.add(new Produto(14, "Mouse Gamer", 180.00, 45));
        produtos.add(new Produto(15, "Teclado Mecânico", 450.00, 30));
        produtos.add(new Produto(16, "Webcam HD", 220.00, 25));

        // Armazenamento
        produtos.add(new Produto(17, "SSD 500GB", 300.00, 50));
        produtos.add(new Produto(18, "HD Externo 1TB", 400.00, 35));
        produtos.add(new Produto(19, "Pendrive 128GB", 80.00, 60));

        // Rede
        produtos.add(new Produto(20, "Roteador Wi-Fi", 280.00, 22));

        return produtos;
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
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


