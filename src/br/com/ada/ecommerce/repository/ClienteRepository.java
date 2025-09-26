package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    private final List<Cliente> clientes = new ArrayList<>();
    private Long proximoId = 1L;

    public void salvar(Cliente cliente) {
        cliente.setId(proximoId++);
        clientes.add(cliente);
    }

    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }

    public Cliente buscarPorDocumento(String documento) {
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equalsIgnoreCase(documento)) {
                return cliente;
            }
        }
        return null;
    }

    public void atualizar(Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            if (cliente.getId().equals(clienteAtualizado.getId())) {
                clientes.set(i, clienteAtualizado);
                return;
            }
        }
    }
}