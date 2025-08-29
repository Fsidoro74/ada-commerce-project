package br.com.ada.ecommerce.main;

import br.com.ada.ecommerce.model.Cliente;
import br.com.ada.ecommerce.notificacao.Notificador;

public class NotificadorEmail implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        // Simulação de envio de e-mail
        System.out.println("Enviando e-mail para " + cliente.getDocumento() + ": " + mensagem);
    }
}
