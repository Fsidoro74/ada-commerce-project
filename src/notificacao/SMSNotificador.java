package br.com.ada.ecommerce.notificacao;

import br.com.ada.ecommerce.model.Cliente;

public class SMSNotificador implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Enviando SMS para " + cliente.getDocumento() + ": " + mensagem);
    }
}
