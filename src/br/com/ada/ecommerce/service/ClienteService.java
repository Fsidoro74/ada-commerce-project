package br.com.ada.ecommerce.service;

import br.com.ada.ecommerce.model.Cliente;
import br.com.ada.ecommerce.repository.ClienteRepository;

import java.util.List;

public class ClienteService {

    private final ClienteRepository clienteRepository;
    private Long ultimoId = 0L;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Cadastra um novo cliente.
     */
    public Cliente cadastrarCliente(String nome, String email, String documento) {
        // Verifica se já existe cliente com este documento
        if (buscarPorDocumento(documento) != null) {
            throw new IllegalArgumentException("Documento já cadastrado no sistema");
        }

        // Gera ID único
        Long id = ++ultimoId;
        Cliente novoCliente = new Cliente(id, nome, email, documento);
        clienteRepository.salvar(novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
        return novoCliente;
    }

    /**
     * Busca cliente por documento (CPF ou CNPJ).
     */
    public Cliente buscarPorDocumento(String documento) {
        return clienteRepository.buscarTodos().stream()
                                .filter(c -> c.getDocumento().equals(documento))
                                .findFirst()
                                .orElse(null);
    }

    /**
     * Lista todos os clientes.
     */
    public List<Cliente> listarTodos() {
        return clienteRepository.buscarTodos();
    }

    /**
     * Remove cliente pelo ID.
     */
    public void removerCliente(Long id) {
        clienteRepository.remover(id);
        System.out.println("Cliente removido com sucesso!");
    }
}
