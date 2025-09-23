package br.com.ada.ecommerce.model;

public enum StatusPedido {
    ABERTO,
    AGUARDANDO_PAGAMENTO,
    PAGO,
    FINALIZADO;

    // Retorna uma descrição amigável para interfaces digitais
    public String getDescricao() {
        switch (this) {
            case ABERTO:
                return "Pedido aberto";
            case AGUARDANDO_PAGAMENTO:
                return "Aguardando pagamento";
            case PAGO:
                return "Pagamento realizado";
            case FINALIZADO:
                return "Pedido finalizado";
            default:
                return "Status desconhecido";
        }
    }

    // Verifica se o status permite edição de itens
    public boolean permiteEditarItens() {
        return this == ABERTO;
    }

    // Verifica se o status permite pagamento
    public boolean podeSerPago() {
        return this == AGUARDANDO_PAGAMENTO;
    }

    // Verifica se o status permite entrega
    public boolean podeSerEntregue() {
        return this == PAGO;
    }
}