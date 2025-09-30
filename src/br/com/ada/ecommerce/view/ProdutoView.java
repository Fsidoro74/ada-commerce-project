package br.com.ada.ecommerce.view;

import br.com.ada.ecommerce.model.Produto;
import br.com.ada.ecommerce.repository.ProdutoRepository;

import java.util.Optional;
import java.util.Scanner;

public class ProdutoView {

    private final Scanner scanner;
    private final ProdutoRepository produtoRepository;

    public ProdutoView(Scanner scanner, ProdutoRepository produtoRepository) {
        this.scanner = scanner;
        this.produtoRepository = produtoRepository;
    }

    public void exibirMenu() {
        boolean executando = true;

        while (executando) {
            System.out.println("\n📦 Menu de Produtos:");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Atualizar produto");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> listarProdutos();
                case 3 -> atualizarProduto();
                case 0 -> executando = false;
                default -> System.out.println("❌ Opção inválida.");
            }
        }
    }

    public void cadastrarProduto() {
        System.out.println("📋 Cadastro de Produto");

        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Preço base: ");
        double preco = scanner.nextDouble();

        System.out.print("Quantidade em estoque: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        Produto produto = new Produto(codigo, nome, preco, quantidade);
        produtoRepository.salvar(produto);

        System.out.println("✅ Produto cadastrado com sucesso.");
    }

    public void listarProdutos() {
        System.out.println("\n📦 Lista de Produtos:");
        for (Produto produto : produtoRepository.listarTodos()) {
            System.out.printf("ID: %d | Código: %s | Nome: %s | Preço: R$ %.2f | Estoque: %d\n",
                    produto.getId(), produto.getCodigo(), produto.getNome(), produto.getPreco(), produto.getQuantidade());
        }
    }

    public void atualizarProduto() {
        System.out.print("Digite o nome do produto que deseja atualizar: ");
        String nome = scanner.nextLine();

        Optional<Produto> produtoOpt = produtoRepository.buscarPorNome(nome);
        if (produtoOpt.isEmpty()) {
            System.out.println("❌ Produto não encontrado.");
            return;
        }

        Produto produto = produtoOpt.get();

        System.out.print("Novo preço: ");
        double novoPreco = scanner.nextDouble();

        System.out.print("Nova quantidade em estoque: ");
        int novaQuantidade = scanner.nextInt();
        scanner.nextLine();

        produto.setPreco(novoPreco);
        produto.setQuantidade(novaQuantidade);

        produtoRepository.atualizar(produto);
        System.out.println("✅ Produto atualizado com sucesso.");
    }
}