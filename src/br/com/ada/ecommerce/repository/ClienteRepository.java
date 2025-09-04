package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional; // Para um retorno mais seguro ao buscar

public class ClienteRepository {
    private final List<Cliente> clientes = new ArrayList<>();

    /**
     * Salva um novo cliente na lista.
     * @param cliente O cliente a ser salvo.
     */
    public void salvar(Cliente cliente) {
        // Em um sistema real, você verificaria se o cliente já existe por ID/documento
        // Para este exercício em memória, simplesmente adicionamos.
        this.clientes.add(cliente);
        System.out.println("Cliente " + cliente.getNome() + " salvo no repositório.");
    }

    /**
     * Lista todos os clientes atualmente no repositório.
     * @return Uma lista imutável de todos os clientes.
     */
    public List<Cliente> listarTodos() {
        return Collections.unmodifiableList(clientes);
    }

    /**
     * Busca um cliente pelo seu documento de identificação.
     * @param documento O documento do cliente a ser buscado.
     * @return Um Optional contendo o cliente, se encontrado; Optional.empty() caso contrário.
     */
    public Optional<Cliente> buscarPorDocumento(String documento) {
        return clientes.stream()
                .filter(c -> c.getDocumento().equals(documento))
                .findFirst();
    }

    /**
     * Busca um cliente pelo seu ID.
     * @param id O ID do cliente a ser buscado.
     * @return Um Optional contendo o cliente, se encontrado; Optional.empty() caso contrário.
     */
    public Optional<Cliente> buscarPorId(long id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }
}
