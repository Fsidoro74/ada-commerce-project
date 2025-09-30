package br.com.ada.ecommerce.service;

import br.com.ada.ecommerce.model.Cliente;
import br.com.ada.ecommerce.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

public class ClienteService {

    private final ClienteRepository clienteRepository;
    private Long ultimoId = 0L;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Cadastra um novo cliente com validações.
     */
    public Cliente cadastrarCliente(String nome, String email, String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("Documento é obrigatório.");
        }

        if (buscarPorDocumento(documento).isPresent()) {
            throw new IllegalArgumentException("Documento já cadastrado no sistema.");
        }

        Long id = ++ultimoId;
        Cliente novoCliente = new Cliente(id, nome, documento, email);
        clienteRepository.salvar(novoCliente);
        return novoCliente;
    }

    /**
     * Busca cliente por documento (CPF ou RG).
     */
    public Optional<Cliente> buscarPorDocumento(String documento) {
        return clienteRepository.listarTodos().stream()
                .filter(c -> c.getDocumento().equalsIgnoreCase(documento))
                .findFirst();
    }

    /**
     * Lista todos os clientes.
     */
    public List<Cliente> listarTodos() {
        return clienteRepository.listarTodos();
    }

    /**
     * Remove cliente pelo ID.
     */
    public boolean removerCliente(Long id) {
        Optional<Cliente> cliente = clienteRepository.listarTodos().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        if (cliente.isPresent()) {
            clienteRepository.remover(id);
            return true;
        }

        return false;
    }
}