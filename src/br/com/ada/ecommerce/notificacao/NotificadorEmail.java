package br.com.ada.ecommerce.notificacao;

import br.com.ada.ecommerce.model.Cliente;

public class NotificadorEmail implements br.com.ada.ecommerce.notificacao.Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        // Simulação de envio de e-mail
        System.out.println("📧 Enviando e-mail para " + cliente.getDocumento() + ": " + mensagem);
    }
}
