package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Cliente;
<<<<<<< HEAD
<<<<<<< HEAD

import java.util.ArrayList;
import java.util.Collections;
=======
import java.util.ArrayList;
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
=======

import java.util.ArrayList;
import java.util.Collections;
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
import java.util.List;
import java.util.Optional;

public class ClienteRepository {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
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
<<<<<<< HEAD
=======
    private List<Cliente> clientes = new ArrayList<>();

    public void salvar(Cliente cliente) {
        clientes.add(new Cliente(cliente.getId(), cliente.getNome(), cliente.getCpf()));
    }

    public List<Cliente> buscarTodos() {
        return new ArrayList<>(clientes);
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clientes.stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst();
    }

    public boolean existePorCpf(String cpf) {
        return clientes.stream()
                .anyMatch(cliente -> cliente.getCpf().equals(cpf));
>>>>>>> 6a55be8a4cde4c9b101a7e74b09a0774750662ae
=======
>>>>>>> 49e9eb5dfd2bc2dcb0b89b08be7376bb42488f89
    }
}
