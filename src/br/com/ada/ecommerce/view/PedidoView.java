public void aplicarCupom(Pedido pedido) {
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