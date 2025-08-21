package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    public List<Produto> buscarTodos() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1, "Notebook", 3500.00));
        produtos.add(new Produto(2, "Celular", 2200.00));
        produtos.add(new Produto(3, "Fone Bluetooth", 300.00));
        return produtos;
    }
}


