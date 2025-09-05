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
        this.clientes.add(cliente);
        System.out.println("Cliente " + cliente.getNome() + " salvo no repositório.");
    }

    /**
     * Retorna todos os clientes cadastrados.
     * @return Lista imutável de clientes.
     */
    public List<Cliente> buscarTodos() { // ✅ nome alinhado com ClienteService
        return Collections.unmodifiableList(clientes);
    }

    /**
     * Busca um cliente pelo ID.
     * @param id ID do cliente.
     * @return Optional contendo o cliente, se encontrado.
     */
    public Optional<Cliente> buscarPorId(Long id) {
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
        return clientes.stream()
                       .filter(c -> c.getDocumento().equalsIgnoreCase(documento))
                       .findFirst();
    }

    /**
     * Remove um cliente pelo ID.
     * @param id ID do cliente a ser removido.
     */
    public void remover(Long id) { // ✅ adicionado para alinhar com ClienteService
        clientes.removeIf(c -> c.getId().equals(id));
    }
}
