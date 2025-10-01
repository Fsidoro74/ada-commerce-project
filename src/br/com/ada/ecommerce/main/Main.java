package br.com.ada.ecommerce.main;

import br.com.ada.ecommerce.model.Cupom;
import br.com.ada.ecommerce.model.Pedido;
import br.com.ada.ecommerce.notificacao.EmailNotificador;
import br.com.ada.ecommerce.notificacao.Notificador;
import br.com.ada.ecommerce.notificacao.SMSNotificador;
import br.com.ada.ecommerce.repository.ClienteRepository;
import br.com.ada.ecommerce.repository.CupomRepository;
import br.com.ada.ecommerce.repository.ProdutoRepository;
import br.com.ada.ecommerce.service.CupomService;
import br.com.ada.ecommerce.service.PedidoService;
import br.com.ada.ecommerce.view.ClienteView;
import br.com.ada.ecommerce.view.CupomView;
import br.com.ada.ecommerce.view.PedidoView;
import br.com.ada.ecommerce.view.ProdutoView;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Repositórios
        ClienteRepository clienteRepository = new ClienteRepository();
        ProdutoRepository produtoRepository = new ProdutoRepository();
        CupomRepository cupomRepository = new CupomRepository();

        // Cupons pré-cadastrados
        cupomRepository.salvar(new Cupom(null, "ADA10", 10, true, LocalDate.now().plusDays(10)));
        cupomRepository.salvar(new Cupom(null, "FIXO50", 50, false, LocalDate.now().plusDays(5)));

        // Views
        ClienteView clienteView = new ClienteView(scanner, clienteRepository);
        ProdutoView produtoView = new ProdutoView(scanner, produtoRepository);
        CupomView cupomView = new CupomView(scanner, cupomRepository);
        PedidoView pedidoView;

        // Notificador
        System.out.println("Escolha o tipo de notificação:");
        System.out.println("1 - E-mail");
        System.out.println("2 - SMS");
        System.out.print("Opção: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        Notificador notificador = (tipo == 2) ? new SMSNotificador() : new EmailNotificador();
        PedidoService pedidoService = new PedidoService(notificador);
        CupomService cupomService = new CupomService(cupomRepository);
        pedidoView = new PedidoView(scanner, pedidoService, produtoRepository, cupomService);

        Pedido pedidoAtual = null;
        boolean executando = true;

        System.out.println("--- Ada Commerce Iniciado ---");

        while (executando) {
            System.out.println("\n📋 Menu Principal:");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Cadastrar produto");
            System.out.println("3 - Criar pedido");
            System.out.println("4 - Adicionar item ao pedido");
            System.out.println("5 - Finalizar pedido");
            System.out.println("6 - Pagar pedido");
            System.out.println("7 - Entregar pedido");
            System.out.println("8 - Aplicar cupom de desconto");
            System.out.println("9 - Listar cupons disponíveis");
            System.out.println("10 - Atualizar cupom de desconto");
            System.out.println("11 - Atualizar cliente");
            System.out.println("12 - Atualizar produto");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> clienteView.cadastrarCliente();
                case 2 -> produtoView.cadastrarProduto();
                case 3 -> pedidoAtual = pedidoView.criarPedido(clienteRepository);
                case 4 -> {
                    if (pedidoAtual == null) {
                        System.out.println("⚠️ Crie um pedido primeiro (opção 3).");
                        break;
                    }
                    pedidoView.adicionarItemAoPedido(pedidoAtual);
                }
                case 5 -> {
                    if (pedidoAtual == null) {
                        System.out.println("⚠️ Crie um pedido primeiro (opção 3).");
                        break;
                    }
                    pedidoView.finalizarPedido(pedidoAtual);
                }
                case 6 -> {
                    if (pedidoAtual == null) {
                        System.out.println("⚠️ Crie um pedido primeiro (opção 3).");
                        break;
                    }
                    pedidoView.pagarPedido(pedidoAtual);
                }
                case 7 -> {
                    if (pedidoAtual == null) {
                        System.out.println("⚠️ Crie um pedido primeiro (opção 3).");
                        break;
                    }
                    pedidoView.entregarPedido(pedidoAtual);
                }
                case 8 -> {
                    if (pedidoAtual == null) {
                        System.out.println("⚠️ Crie um pedido primeiro (opção 3).");
                        break;
                    }
                    pedidoView.aplicarCupom(pedidoAtual);
                }
                case 9 -> cupomView.listarCupons();
                case 10 -> cupomView.atualizarCupom();
                case 11 -> clienteView.atualizarCliente();
                case 12 -> produtoView.atualizarProduto();
                case 0 -> {
                    executando = false;
                    System.out.println("👋 Encerrando o sistema...");
                }
                default -> System.out.println("❌ Opção inválida.");
            }
        }
        scanner.close();
    }
}