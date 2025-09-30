package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepository {

    private final List<Cliente> clientes = new ArrayList<>();
    private Long proximoId = 1L;

    public void salvar(Cliente cliente) {
        if (buscarPorDocumento(cliente.getDocumento()).isPresent()) {
            throw new IllegalArgumentException("Cliente com este documento já existe.");
        }
        cliente.setId(proximoId++);
        clientes.add(cliente);
    }

    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }

    public Optional<Cliente> buscarPorDocumento(String documento) {
        return clientes.stream()
                .filter(c -> c.getDocumento().equalsIgnoreCase(documento))
                .findFirst();
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        return buscarPorDocumento(cpf); // Reutiliza a lógica existente
    }

    public void atualizar(Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(clienteAtualizado.getId())) {
                clientes.set(i, clienteAtualizado);
                return;
            }
        }
        throw new IllegalArgumentException("Cliente não encontrado para atualização.");
    }

    public void remover(Long id) {
        clientes.removeIf(c -> c.getId().equals(id));
    }
}