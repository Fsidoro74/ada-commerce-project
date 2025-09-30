package br.com.ada.ecommerce.view;

import br.com.ada.ecommerce.model.*;
import br.com.ada.ecommerce.repository.ClienteRepository;
import br.com.ada.ecommerce.repository.ProdutoRepository;
import br.com.ada.ecommerce.service.CupomService;
import br.com.ada.ecommerce.service.PedidoService;

import java.util.Optional;
import java.util.Scanner;

public class PedidoView {

    private final Scanner scanner;
    private final PedidoService pedidoService;
    private final ProdutoRepository produtoRepository;
    private final CupomService cupomService;

    public PedidoView(Scanner scanner, PedidoService pedidoService, ProdutoRepository produtoRepository, CupomService cupomService) {
        this.scanner = scanner;
        this.pedidoService = pedidoService;
        this.produtoRepository = produtoRepository;
        this.cupomService = cupomService;
    }

    public Pedido criarPedido(ClienteRepository clienteRepository) {
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();
        Optional<Cliente> clienteOpt = clienteRepository.buscarPorCpf(cpf);

        if (clienteOpt.isEmpty()) {
            System.out.println("❌ Cliente não encontrado.");
            return null;
        }

        Pedido pedido = new Pedido(clienteOpt.get());
        System.out.println("✅ Pedido criado para " + clienteOpt.get().getNome());
        return pedido;
    }

    public void adicionarItemAoPedido(Pedido pedido) {
        if (pedido == null) {
            System.out.println("⚠️ Nenhum pedido em andamento.");
            return;
        }

        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine();
        Optional<Produto> produtoOpt = produtoRepository.buscarPorCodigo(codigo);

        if (produtoOpt.isEmpty()) {
            System.out.println("❌ Produto não encontrado.");
            return;
        }

        Produto produto = produtoOpt.get();
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Preço de venda: ");
        double precoVenda = scanner.nextDouble();
        scanner.nextLine();

        pedidoService.adicionarItemAoPedido(pedido, produto, quantidade, precoVenda);
        System.out.println("✅ Item adicionado ao pedido.");
    }

    public void finalizarPedido(Pedido pedido) {
        if (pedido == null) {
            System.out.println("⚠️ Nenhum pedido em andamento.");
            return;
        }

        try {
            pedidoService.finalizarPedido(pedido);
            System.out.println("✅ Pedido finalizado. Status: " + pedido.getStatus());
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    public void pagarPedido(Pedido pedido) {
        if (pedido == null) {
            System.out.println("⚠️ Nenhum pedido em andamento.");
            return;
        }

        try {
            pedidoService.pagarPedido(pedido);
            System.out.println("✅ Pedido pago. Status: " + pedido.getStatus());
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    public void entregarPedido(Pedido pedido) {
        if (pedido == null) {
            System.out.println("⚠️ Nenhum pedido em andamento.");
            return;
        }

        try {
            pedidoService.entregarPedido(pedido);
            System.out.println("✅ Pedido entregue. Status: " + pedido.getStatus());
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    public void aplicarCupom(Pedido pedido) {
        if (pedido == null) {
            System.out.println("⚠️ Nenhum pedido em andamento.");
            return;
        }

        System.out.print("Código do cupom: ");
        String codigo = scanner.nextLine();
        Optional<Cupom> cupomOpt = cupomService.buscarPorCodigo(codigo);

        if (cupomOpt.isEmpty()) {
            System.out.println("❌ Cupom não encontrado.");
            return;
        }

        Cupom cupom = cupomOpt.get();

        try {
            pedidoService.aplicarCupom(pedido, cupom);
            System.out.println("✅ Cupom aplicado. Desconto: R$ " + cupom.aplicarDesconto(pedido.calcularTotal()));
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
}