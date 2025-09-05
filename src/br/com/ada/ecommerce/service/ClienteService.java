package br.com.ada.ecommerce.service;

import br.com.ada.ecommerce.model.Cliente;
import br.com.ada.ecommerce.repository.ClienteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteService {
    private List<Cliente> clientes = new ArrayList<>();
    private int ultimoId = 0;

    public Cliente cadastrarCliente(String nome, String cpf) {
        // Verifica se já existe cliente com este CPF
        if (buscarPorCpf(cpf) != null) {
            throw new IllegalArgumentException("CPF já cadastrado no sistema");
        }

        // Gera um ID único
        int id = ++ultimoId;
        Cliente novoCliente = new Cliente(id, nome, cpf);
        clientes.add(novoCliente);
        return novoCliente;
    }

    public Cliente buscarPorCpf(String cpf) {
        return clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }
}
