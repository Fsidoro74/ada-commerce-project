package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ClienteRepository {

    private final List<Cliente> clientes = new ArrayList<>();

    /**
     * Salva um novo cliente na lista.
     * @param cliente O cliente a ser salvo.
     */
    public void salvar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        clientes.add(cliente);
    }

    /**
     * Retorna todos os clientes cadastrados.
     * @return Lista imutável de clientes.
     */
    public List<Cliente> buscarTodos() {
        return Collections.unmodifiableList(new ArrayList<>(clientes));
    }

    /**
     * Busca um cliente pelo ID.
     * @param id ID do cliente.
     * @return Optional contendo o cliente, se encontrado.
     */
    public Optional<Cliente> buscarPorId(Long id) {
        if (id == null) return Optional.empty();

        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    /**
     * Busca um cliente pelo documento (CPF ou CNPJ).
     * @param documento Documento do cliente.
     * @return Optional contendo o cliente, se encontrado.
     */
    public Optional<Cliente> buscarPorDocumento(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            return Optional.empty();
        }

        return clientes.stream()
                .filter(c -> c.getDocumento().equalsIgnoreCase(documento.trim()))
                .findFirst();
    }

    /**
     * Remove um cliente pelo ID.
     * @param id ID do cliente a ser removido.
     */
    public void remover(Long id) {
        if (id == null) return;
        clientes.removeIf(c -> c.getId().equals(id));
    }
}