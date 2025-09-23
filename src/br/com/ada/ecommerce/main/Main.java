package br.com.ada.ecommerce.main;

import br.com.ada.ecommerce.model.Cliente;
import br.com.ada.ecommerce.model.Produto;
import br.com.ada.ecommerce.model.Pedido;
import br.com.ada.ecommerce.repository.ClienteRepository;
import br.com.ada.ecommerce.repository.ProdutoRepository;
import br.com.ada.ecommerce.service.PedidoService;
import br.com.ada.ecommerce.notificacao.Notificador;
import br.com.ada.ecommerce.notificacao.EmailNotificador;
import br.com.ada.ecommerce.notificacao.SMSNotificador;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Ada Commerce Iniciado ---");

        Scanner scanner = new Scanner(System.in);

        // Repositórios
        ClienteRepository clienteRepository = new ClienteRepository();
        ProdutoRepository produtoRepository = new ProdutoRepository();

        // Escolher tipo de notificação
        System.out.println("Escolha o tipo de notificação:");
        System.out.println("1 - E-mail");
        System.out.println("2 - SMS");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();

        Notificador notificador = (opcao == 2) ? new SMSNotificador() : new EmailNotificador();
        String tipoNotificacao = (opcao == 2) ? "SMS" : "E-mail";

        System.out.println("\n📢 Notificação escolhida: " + tipoNotificacao + "\n");

        // Serviço de pedidos com notificador
        PedidoService pedidoService = new PedidoService(notificador);

        // 1. Cadastrar cliente
        Cliente cliente1 = new Cliente(1L, "João Silva", "joao@email.com", "12345678901");
        clienteRepository.salvar(cliente1);

        // 2. Cadastrar produtos
        Produto notebook = new Produto(1L, "Notebook", 3500.00, 10);
        Produto celular = new Produto(2L, "Celular", 2200.00, 5);
        produtoRepository.salvar(notebook);
        produtoRepository.salvar(celular);

        // 3. Criar pedido e adicionar itens
        Pedido pedido = new Pedido(1L, cliente1);
        pedidoService.adicionarProdutoAoPedido(pedido, notebook, 1);
        pedidoService.adicionarProdutoAoPedido(pedido, celular, 2);

        // 4. Exibir resumo inicial
        System.out.println("📄 Resumo do Pedido Inicial:");
        System.out.println(pedidoService.gerarResumoPedido(pedido));

        // 5. Finalizar, pagar e entregar
        pedidoService.finalizarPedido(pedido);
        pedidoService.pagar(pedido);
        pedidoService.entregar(pedido);

        // 6. Exibir resumo final
        System.out.println("📦 Resumo do Pedido Final:");
        System.out.println(pedidoService.gerarResumoPedido(pedido));

        scanner.close();
    }
}