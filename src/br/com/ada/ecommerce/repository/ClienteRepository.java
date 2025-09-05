package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ClienteRepository {
    private final List<Cliente> clientes = new ArrayList<>();

    /**
     * Save or update a client by ID.
     */
    public void salvar(Cliente cliente) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == cliente.getId()) {
                clientes.set(i, cliente);
                return;
            }
        }
        clientes.add(cliente);
    }

    /**
     * Read-only view of all clients.
     */
    public List<Cliente> listarTodos() {
        return Collections.unmodifiableList(clientes);
    }

    /**
     * Find by document.
     */
    public Optional<Cliente> buscarPorDocumento(String documento) {
        return clientes.stream()
                .filter(c -> c.getDocumento() != null && c.getDocumento().equals(documento))
                .findFirst();
    }

    /**
     * Find by ID.
     */
    public Optional<Cliente> buscarPorId(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }
}
