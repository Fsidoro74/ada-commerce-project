package br.com.ada.ecommerce.notificacao;

import br.com.ada.ecommerce.model.Cliente;

public class EmailNotificador implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        // Simulação silenciosa de envio de e-mail
    }

    @Override
    public String gerarMensagemSimulada(Cliente cliente, String mensagem) {
        return String.format("📧 E-mail para %s (%s): %s",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}