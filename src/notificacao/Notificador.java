package br.com.ada.ecommerce.notificacao;

import br.com.ada.ecommerce.model.Cliente;

public interface Notificador {

    /**
     * Envia uma notificação para o cliente.
     */
    void notificar(Cliente cliente, String mensagem);

    /**
     * Gera uma representação textual da notificação.
     * Útil para interfaces digitais, testes ou logs.
     */
    default String gerarMensagemSimulada(Cliente cliente, String mensagem) {
        return String.format("📢 Notificação para %s (%s): %s",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}