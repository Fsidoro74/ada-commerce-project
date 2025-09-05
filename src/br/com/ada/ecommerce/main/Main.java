package br.com.ada.ecommerce.main;

import br.com.ada.ecommerce.model.Carrinho;
import br.com.ada.ecommerce.model.Cliente;
import br.com.ada.ecommerce.model.Pedido;
import br.com.ada.ecommerce.model.Produto;
import br.com.ada.ecommerce.notificacao.EmailNotificador;
import br.com.ada.ecommerce.notificacao.Notificador;
import br.com.ada.ecommerce.repository.ClienteRepository;
import br.com.ada.ecommerce.repository.PedidoRepository;
import br.com.ada.ecommerce.repository.ProdutoRepository;
import br.com.ada.ecommerce.service.PedidoService;
import br.com.ada.ecommerce.model.StatusPedido;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Inicialização de Repositórios e Serviços
        ClienteRepository clienteRepository = new ClienteRepository();
        ProdutoRepository produtoRepository = new ProdutoRepository();
        PedidoRepository pedidoRepository = new PedidoRepository();

<<<<<<< HEAD
        Notificador notificador = new EmailNotificador(); // Podemos trocar para SMSNotificador facilmente
        PedidoService pedidoService = new PedidoService(notificador);
=======
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new Cliente(1, "João Silva", "123.456.789-09");
        Pedido pedido = new Pedido(cliente);
        ProdutoRepository produtoRepo = new ProdutoRepository();
        PedidoService pedidoService = new PedidoService();
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae

        System.out.println("--- Ada Commerce Iniciado ---");

<<<<<<< HEAD
        // 2. Cadastro de Clientes - IDs são gerados automaticamente pelo construtor de Cliente
        Cliente cliente1 = new Cliente("Fabio Mendes", "123.456.789-00", "fabio.mendes@email.com");
        Cliente cliente2 = new Cliente("Maria Santos", "987.654.321-11", "maria.santos@email.com");
        clienteRepository.salvar(cliente1);
        clienteRepository.salvar(cliente2);
        System.out.println("\nClientes cadastrados:");
        clienteRepository.listarTodos().forEach(c -> System.out.println("- " + c.getNome() + " (ID: " + c.getId() + ")"));
=======
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
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae


<<<<<<< HEAD
        // 3. Cadastro de Produtos
        Produto tv = new Produto(101, "Smart TV LED 50'", 2500.00, 2299.99, 5);
        Produto smartphone = new Produto(102, "Smartphone Android 12", 1500.00, 1400.00, 10);
        Produto foneOuvido = new Produto(103, "Fone de Ouvido Bluetooth", 150.00, 120.00, 20);
        produtoRepository.salvar(tv);
        produtoRepository.salvar(smartphone);
        produtoRepository.salvar(foneOuvido);
        System.out.println("\nProdutos em estoque:");
        produtoRepository.listarTodos().forEach(p -> System.out.println("- " + p.getNome() + " (Estoque: " + p.getQuantidade() + ")"));


        // 4. Fluxo de Compra para o Cliente João
        System.out.println("\n--- Fluxo de Compra para " + cliente1.getNome() + " ---");
        Carrinho carrinhoJoao = new Carrinho();

        try {
            carrinhoJoao.adicionarItem(tv, 1);
            carrinhoJoao.adicionarItem(smartphone, 2);
            carrinhoJoao.adicionarItem(foneOuvido, 3);
            carrinhoJoao.exibirCarrinho();

            System.out.println("\nTentando adicionar mais TV (Estoque: " + tv.getQuantidade() + ")");
            carrinhoJoao.adicionarItem(tv, 5); // Deve dar erro de estoque
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao adicionar item ao carrinho: " + e.getMessage());
        }

        try {
            carrinhoJoao.removerItem(foneOuvido);
            carrinhoJoao.exibirCarrinho();

            carrinhoJoao.alterarQuantidadeItem(smartphone, 1);
            carrinhoJoao.exibirCarrinho();
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao manipular item no carrinho: " + e.getMessage());
        }

        // Finalizar o Pedido
        Pedido pedidoJoao = null;
        try {
            // Criamos o pedido a partir do carrinho e do cliente
            pedidoJoao = new Pedido(cliente1);
            for (var itemVenda : carrinhoJoao.getItens()) {
                pedidoJoao.adicionarProduto(itemVenda.getProduto(), itemVenda.getQuantidade());
            }
=======
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
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae

            pedidoService.finalizarPedido(pedidoJoao);
            pedidoRepository.salvar(pedidoJoao);
            pedidoJoao.exibirResumo();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Erro ao finalizar pedido: " + e.getMessage());
        }

        // Realizar Pagamento
        if (pedidoJoao != null && pedidoJoao.getStatus() == StatusPedido.AGUARDANDO_PAGAMENTO) {
            try {
                pedidoService.pagar(pedidoJoao);
            } catch (IllegalStateException e) {
                System.err.println("Erro ao pagar pedido: " + e.getMessage());
            }
        }

        // Realizar Entrega
        if (pedidoJoao != null && pedidoJoao.getStatus() == StatusPedido.PAGO) {
            try {
                pedidoService.entregar(pedidoJoao);
            } catch (IllegalStateException e) {
                System.err.println("Erro ao entregar pedido: " + e.getMessage());
            }
        }

        System.out.println("\nEstoque de produtos após a compra:");
        produtoRepository.listarTodos().forEach(p -> System.out.println("- " + p.getNome() + " (Estoque: " + p.getQuantidade() + ")"));

        // 5. Consultar Histórico de Pedidos do Cliente João
        System.out.println("\n--- Histórico de Pedidos de " + cliente1.getNome() + " ---");
        List<Pedido> pedidosDoJoao = pedidoRepository.buscarPorClienteId(cliente1.getId());
        if (pedidosDoJoao.isEmpty()) {
            System.out.println("Nenhum pedido encontrado para " + cliente1.getNome());
        } else {
            pedidosDoJoao.forEach(p -> System.out.println("Pedido #" + p.getId() + " - Total: R$ " + String.format("%.2f", p.calcularTotal()) + " - Status: " + p.getStatus()));
        }

        // 6. Exemplo: Como fazer um novo pedido (novo carrinho) para o mesmo cliente
        System.out.println("\n--- Novo Pedido para " + cliente1.getNome() + " ---");
        Carrinho novoCarrinho = new Carrinho();
        try {
            novoCarrinho.adicionarItem(foneOuvido, 2); // adiciona 2 fones
            novoCarrinho.exibirCarrinho();

            Pedido novoPedido = new Pedido(cliente1);
            for (var itemVenda : novoCarrinho.getItens()) {
                novoPedido.adicionarProduto(itemVenda.getProduto(), itemVenda.getQuantidade());
            }

            pedidoService.finalizarPedido(novoPedido);
            pedidoRepository.salvar(novoPedido);
            pedidoService.pagar(novoPedido); // pagar
            pedidoService.entregar(novoPedido); // entregar → FINALIZADO

            novoPedido.exibirResumo();
        } catch (Exception e) {
            System.err.println("Erro no novo pedido: " + e.getMessage());
        }
    }
}