package br.com.ada.ecommerce.view;

import br.com.ada.ecommerce.model.*;
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
            System.out.println("✅ Cupom aplicado.");
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    // outros métodos...
}