package br.com.ada.ecommerce.main;

import br.com.ada.ecommerce.model.Carrinho;
import br.com.ada.ecommerce.model.Cliente;
import br.com.ada.ecommerce.model.Pedido;
import br.com.ada.ecommerce.model.Produto;
import br.com.ada.ecommerce.repository.ClienteRepository;
import br.com.ada.ecommerce.repository.PedidoRepository;
import br.com.ada.ecommerce.repository.ProdutoRepository;
import br.com.ada.ecommerce.service.PedidoService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Inicialização de Repositórios e Serviços
        ClienteRepository clienteRepository = new ClienteRepository();
        ProdutoRepository produtoRepository = new ProdutoRepository();
        PedidoRepository pedidoRepository = new PedidoRepository();
        PedidoService pedidoService = new PedidoService();

        System.out.println("--- Ada Commerce Iniciado ---");

        // 2. Cadastro de Clientes - IDs são gerados automaticamente pelo construtor de Cliente
        Cliente cliente1 = new Cliente("Fabio Mendes", "123.456.789-00", "fabio.mendes@email.com");
        Cliente cliente2 = new Cliente("Maria Santos", "987.654.321-11", "maria.santos@email.com");
        clienteRepository.salvar(cliente1);
        clienteRepository.salvar(cliente2);
        System.out.println("\nClientes cadastrados:");
        clienteRepository.listarTodos().forEach(c -> System.out.println("- " + c.getNome() + " (ID: " + c.getId() + ")"));

        // 3. Cadastro de Produtos
        Produto tv = new Produto(101, "Smart TV LED 50'", 2500.00, 2299.99, 5);
        Produto smartphone = new Produto(102, "Smartphone Android 12", 1500.00, 1400.00, 10);
        Produto foneOuvido = new Produto(103, "Fone de Ouvido Bluetooth", 150.00, 120.00, 20);
        produtoRepository.salvar(tv);
        produtoRepository.salvar(smartphone);
        produtoRepository.salvar(foneOuvido);

        Scanner scanner = new Scanner(System.in);
        Pedido pedido = new Pedido(cliente1);
        Carrinho carrinho = new Carrinho();

        int opcao;
        do {
            System.out.println("\n=== Menu E-commerce ===");
            System.out.println("1. Ver produtos disponíveis");
            System.out.println("2. Adicionar produto ao carrinho");
            System.out.println("3. Ver carrinho");
            System.out.println("4. Finalizar pedido");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, digite um número válido: ");
                scanner.next();
            }
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1: {
                    System.out.println("\n📦 Produtos disponíveis:");
                    List<Produto> produtosDisponiveis = produtoRepository.listarTodos();
                    for (int i = 0; i < produtosDisponiveis.size(); i++) {
                        Produto p = produtosDisponiveis.get(i);
                        System.out.println((i + 1) + " - " + p.getNome() + " (R$ " + p.getPreco() + ") - Estoque: " + p.getQuantidade());
                    }
                    break;
                }
                case 2: {
                    List<Produto> lista = produtoRepository.listarTodos();
                    if (lista.isEmpty()) {
                        System.out.println("Sem produtos cadastrados.");
                        break;
                    }
                    System.out.print("\nDigite o número do produto para adicionar: ");
                    int escolha = scanner.nextInt();
                    if (escolha > 0 && escolha <= lista.size()) {
                        Produto escolhido = lista.get(escolha - 1);
                        System.out.print("Quantidade: ");
                        int quantidade = scanner.nextInt();
                        try {
                            carrinho.adicionarItem(escolhido, quantidade);
                            System.out.println("✅ Produto adicionado: " + escolhido.getNome());
                        } catch (IllegalArgumentException e) {
                            System.out.println("⚠ " + e.getMessage());
                        }
                    } else {
                        System.out.println("⚠ Produto inválido!");
                    }
                    break;
                }
                case 3: {
                    System.out.println("\n🛒 Carrinho:");
                    carrinho.exibirCarrinho();
                    break;
                }
                case 4: {
                    if (carrinho.getItens().isEmpty()) {
                        System.out.println("⚠ Carrinho vazio. Adicione itens antes de finalizar.");
                        break;
                    }
                    // Copia itens do carrinho para o pedido (um Produto por unidade)
                    for (var item : carrinho.getItens()) {
                        for (int i = 0; i < item.getQuantidade(); i++) {
                            pedidoService.adicionarProdutoAoPedido(pedido, item.getProduto());
                        }
                    }
                    pedidoRepository.salvar(pedido);
                    System.out.println("📌 Pedido finalizado com sucesso!\n");
                    pedido.exibirResumo();
                    break;
                }
                case 5: {
                    System.out.println("👋 Saindo do sistema...");
                    break;
                }
                default:
                    System.out.println("⚠ Opção inválida!");
            }
        } while (opcao != 5);

        scanner.close();
    }
}