package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
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
    }
}


