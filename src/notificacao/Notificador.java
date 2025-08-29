package br.com.ada.ecommerce.notificacao;

import br.com.ada.ecommerce.model.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}

