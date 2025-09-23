package br.com.ada.ecommerce.notificacao;

import br.com.ada.ecommerce.model.Cliente;

public class SMSNotificador implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        // Simulação silenciosa de envio de SMS
    }

    @Override
    public String gerarMensagemSimulada(Cliente cliente, String mensagem) {
        return String.format("📱 SMS para %s (%s): %s",
                cliente.getNome(), cliente.getDocumento(), mensagem);
    }
}