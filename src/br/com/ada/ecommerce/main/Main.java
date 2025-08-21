package br.com.ada.ecommerce.main;

import br.com.ada.ecommerce.model.Cliente;
import br.com.ada.ecommerce.model.Pedido;
import br.com.ada.ecommerce.model.Produto;
import br.com.ada.ecommerce.repository.ProdutoRepository;
import br.com.ada.ecommerce.service.PedidoService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new Cliente(1, "João Silva");
        Pedido pedido = new Pedido(cliente);
        ProdutoRepository produtoRepo = new ProdutoRepository();
        PedidoService pedidoService = new PedidoService();

        List<Produto> produtosDisponiveis = produtoRepo.buscarTodos();

        int opcao;
        do {
            System.out.println("\n🛒 Menu E-commerce:");
            System.out.println("1. Ver produtos disponíveis");
            System.out.println("2. Adicionar produto ao carrinho");
            System.out.println("3. Ver carrinho");
            System.out.println("4. Finalizar pedido");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\n📦 Produtos:");
                    for (int i = 0; i < produtosDisponiveis.size(); i++) {
                        Produto p = produtosDisponiveis.get(i);
                        System.out.println(i + " - " + p.getNome() + " (R$ " + p.getPreco() + ")");
                    }
                    break;

                case 2:
                    System.out.print("Digite o número do produto que deseja adicionar: ");
                    int indiceProduto = scanner.nextInt();
                    if (indiceProduto >= 0 && indiceProduto < produtosDisponiveis.size()) {
                        pedidoService.adicionarProdutoAoPedido(pedido, produtosDisponiveis.get(indiceProduto));
                        System.out.println("Produto adicionado!");
                    } else {
                        System.out.println("Produto inválido.");
                    }
                    break;

                case 3:
                    System.out.println("\n🧾 Carrinho:");
                    pedidoService.exibirResumoPedido(pedido);
                    break;

                case 4:
                    System.out.println("✅ Pedido finalizado!");
                    pedidoService.exibirResumoPedido(pedido);
                    break;

                case 5:
                    System.out.println("👋 Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 5);

        scanner.close();
    }
}


