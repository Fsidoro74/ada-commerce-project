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
            System.out.println("5. Realizar pagamento");
            System.out.println("6. Realizar entrega");
            System.out.println("7. Sair");
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
                    System.out.print("Digite o número do produto que deseja adicionar do 1 ao 3: ");
                    int indiceProduto = scanner.nextInt();
                    if (indiceProduto >= 0 && indiceProduto < produtosDisponiveis.size()) {
                        Produto produtoSelecionado = produtosDisponiveis.get(indiceProduto);

                        System.out.print("Quantidade: ");
                        int quantidade = scanner.nextInt();

                        System.out.print("Preço de venda (R$): ");
                        double precoVenda = scanner.nextDouble();

                        pedidoService.adicionarItemAoPedido(pedido, produtoSelecionado, quantidade, precoVenda);
                        System.out.println("✅ Item adicionado ao carrinho!");
                    } else {
                        System.out.println("Digite o número do produto que deseja adicionar (1 ao 3): ");
                    }
                    break;

                case 3:
                    System.out.println("\n🧾 Carrinho:");
                    pedido.exibirResumo();
                    break;

                case 4:
                    if (pedido.podeFinalizar()) {
                        pedido.setStatus(br.com.ada.ecommerce.model.StatusPedido.AGUARDANDO_PAGAMENTO);
                        System.out.println("✅ Pedido finalizado! Status: Aguardando pagamento.");
                        pedido.exibirResumo();
                        // Aqui você pode simular envio de e-mail
                    } else {
                        System.out.println("⚠️ Pedido não pode ser finalizado. Verifique se há itens e valor maior que zero.");
                    }
                    break;

                case 5:
                    pedidoService.realizarPagamento(pedido);
                    break;

                case 6:
                    pedidoService.realizarEntrega(pedido);
                    break;

                case 7:
                    System.out.println("👋 Saindo...");
                    break;


                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 7);

        scanner.close();
    }
}