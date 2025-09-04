package br.com.ada.ecommerce.tests;

import br.com.ada.ecommerce.model.*;
import br.com.ada.ecommerce.notificacao.EmailNotificador;
import br.com.ada.ecommerce.service.PedidoService;

public class TestRunner {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testFluxoPedidoFinalizado();
        testValidacoesPedido();
        testEstoqueCarrinho();

        System.out.println("[TEST_SUMMARY] passed=" + passed + " failed=" + failed);
        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void assertTrue(boolean condition, String name) {
        if (condition) {
            System.out.println("[TEST_PASS] " + name);
            passed++;
        } else {
            System.out.println("[TEST_FAIL] " + name);
            failed++;
        }
    }

    private static void testFluxoPedidoFinalizado() {
        Cliente c = new Cliente("Teste", "000", "t@t.com");
        Produto p = new Produto(1, "Item", 10.0, 8.0, 5);
        Carrinho carrinho = new Carrinho();
        carrinho.adicionarItem(p, 2); // estoque vai para 3

        Pedido pedido = new Pedido(c);
        for (ItemVenda iv : carrinho.getItens()) {
            pedido.adicionarProduto(iv.getProduto(), iv.getQuantidade());
        }
        PedidoService service = new PedidoService(new EmailNotificador());
        service.finalizarPedido(pedido);
        service.pagar(pedido);
        service.entregar(pedido);

        assertTrue(pedido.getStatus() == StatusPedido.FINALIZADO, "Fluxo completa até FINALIZADO");
        assertTrue(p.getQuantidade() == 3, "Estoque decrementado corretamente no carrinho");
    }

    private static void testValidacoesPedido() {
        boolean lancou = false;
        try { new Pedido(null); } catch (IllegalArgumentException e) { lancou = true; }
        assertTrue(lancou, "Pedido nao aceita cliente nulo");

        Cliente c = new Cliente("Teste2", "001", "t2@t.com");
        Pedido pedido = new Pedido(c);
        Produto p = new Produto(2, "Item2", 20.0, 15.0, 10);
        lancou = false;
        try { pedido.adicionarProduto(p, 0); } catch (IllegalArgumentException e) { lancou = true; }
        assertTrue(lancou, "Nao permite quantidade zero");
    }

    private static void testEstoqueCarrinho() {
        Produto p = new Produto(3, "Item3", 30.0, 25.0, 10);
        Carrinho carrinho = new Carrinho();
        carrinho.adicionarItem(p, 4); // estoque 6
        carrinho.alterarQuantidadeItem(p, 2); // devolve 2 -> estoque 8
        assertTrue(p.getQuantidade() == 8, "Alteracao de quantidade ajusta estoque");
        carrinho.removerItem(p); // devolve 2 -> estoque 10
        assertTrue(p.getQuantidade() == 10, "Remocao devolve estoque");
    }
}
