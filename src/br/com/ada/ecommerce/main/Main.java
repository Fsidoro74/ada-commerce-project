package br.com.ada.ecommerce.main;

import br.com.ada.ecommerce.model.*;
import br.com.ada.ecommerce.notificacao.EmailNotificador;
import br.com.ada.ecommerce.notificacao.Notificador;
import br.com.ada.ecommerce.repository.ClienteRepository;
import br.com.ada.ecommerce.repository.PedidoRepository;
import br.com.ada.ecommerce.repository.ProdutoRepository;
import br.com.ada.ecommerce.service.PedidoService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Inicialização de Repositórios e Serviços
        ClienteRepository clienteRepository = new ClienteRepository();
        ProdutoRepository produtoRepository = new ProdutoRepository();
        PedidoRepository pedidoRepository = new PedidoRepository();
        Notificador notificador = new EmailNotificador();
        PedidoService pedidoService = new PedidoService(notificador);

        System.out.println("--- Ada Commerce Iniciado ---");

        // 2. Cadastro de Clientes
        Cliente cliente1 = new Cliente("Fabio Mendes", "123.456.789-00", "fabio.mendes@email.com");
        Cliente cliente2 = new Cliente("Maria Santos", "987.654.321-11", "maria.santos@email.com");
        clienteRepository.salvar(cliente1);
        clienteRepository.salvar(cliente2);

        System.out.println("\nClientes cadastrados:");
        clienteRepository.listarTodos().forEach(c ->
                System.out.println("- " + c.getNome() + " (ID: " + c.getId() + ")"));

        // 3. Cadastro de Produtos
        Produto tv = new Produto(101, "Smart TV LED 50'", 2500.00, 2299.99, 5);
        Produto smartphone = new Produto(102, "Smartphone Android 12", 1500.00, 1400.00, 10);
        Produto foneOuvido = new Produto(103, "Fone de Ouvido Bluetooth", 150.00, 120.00, 20);
        produtoRepository.salvar(tv);
        produtoRepository.salvar(smartphone);
        produtoRepository.salvar(foneOuvido);

        System.out.println("\nProdutos em estoque:");
        produtoRepository.listarTodos().forEach(p ->
                System.out.println("- " + p.getNome() + " (Estoque: " + p.getQuantidade() + ")"));

        // 4. Fluxo de Compra para o Cliente Fabio
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

        // 5. Finalizar o Pedido
        Pedido pedidoJoao = null;
        try {
            pedidoJoao = new Pedido(cliente1);
            for (ItemVenda itemVenda : carrinhoJoao.getItens()) {
                pedidoJoao.adicionarProduto(
                        itemVenda.getProduto(),
                        itemVenda.getQuantidade(),
                        itemVenda.getPrecoVenda() // ✅ Correção aqui
                );
            }

            pedidoService.finalizarPedido(pedidoJoao);
            pedidoRepository.salvar(pedidoJoao);
            pedidoJoao.exibirResumo();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Erro ao finalizar pedido: " + e.getMessage());
        }

        // 6. Pagamento
        if (pedidoJoao != null && pedidoJoao.getStatus() == StatusPedido.AGUARDANDO_PAGAMENTO) {
            try {
                pedidoService.pagar(pedidoJoao);
            } catch (IllegalStateException e) {
                System.err.println("Erro ao pagar pedido: " + e.getMessage());
            }
        }

        // 7. Entrega
        if (pedidoJoao != null && pedidoJoao.getStatus() == StatusPedido.PAGO) {
            try {
                pedidoService.entregar(pedidoJoao);
            } catch (IllegalStateException e) {
                System.err.println("Erro ao entregar pedido: " + e.getMessage());
            }
        }

        // 8. Estoque após compra
        System.out.println("\nEstoque de produtos após a compra:");
        produtoRepository.listarTodos().forEach(p ->
                System.out.println("- " + p.getNome() + " (Estoque: " + p.getQuantidade() + ")"));

        // 9. Histórico de Pedidos
        System.out.println("\n--- Histórico de Pedidos de " + cliente1.getNome() + " ---");
        List<Pedido> pedidosDoJoao = pedidoRepository.buscarPorClienteId(cliente1.getId());
        if (pedidosDoJoao.isEmpty()) {
            System.out.println("Nenhum pedido encontrado para " + cliente1.getNome());
        } else {
            pedidosDoJoao.forEach(p -> {
                System.out.printf("Pedido #%d - Total: R$ %.2f - Status: %s%n",
                        p.getId(), p.calcularTotal(), p.getStatus());
            });
        }
    }
}