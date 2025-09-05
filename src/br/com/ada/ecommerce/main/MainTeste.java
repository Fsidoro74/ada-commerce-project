package br.com.ada.ecommerce.main;

import br.com.ada.ecommerce.model.Cliente;
import br.com.ada.ecommerce.model.Produto;
import br.com.ada.ecommerce.model.Pedido;
import br.com.ada.ecommerce.repository.ClienteRepository;
import br.com.ada.ecommerce.repository.ProdutoRepository;
import br.com.ada.ecommerce.service.PedidoService;
import br.com.ada.ecommerce.notificacao.Notificador;
import br.com.ada.ecommerce.notificacao.NotificadorEmail;
import br.com.ada.ecommerce.notificacao.SMSNotificador;

import java.util.Scanner;

public class MainTeste {
    public static void main(String[] args) {
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

        Notificador notificador;
        String tipoNotificacao;
        if (opcao == 2) {
            notificador = new SMSNotificador();
            tipoNotificacao = "SMS";
        } else {
            notificador = new NotificadorEmail();
            tipoNotificacao = "E-mail";
        }

        // Mostrar escolha do usuário
        System.out.println("\n📢 Notificação escolhida: " + tipoNotificacao + "\n");

        // Serviço com notificação escolhida
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
        pedido.exibirResumo();

        // 5. Finalizar, pagar e entregar
        pedidoService.finalizarPedido(pedido);
        pedidoService.pagar(pedido);
        pedidoService.entregar(pedido);

        // 6. Exibir resumo final
        pedido.exibirResumo();

        scanner.close();
    }
}
