package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepository {
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
    }
}
