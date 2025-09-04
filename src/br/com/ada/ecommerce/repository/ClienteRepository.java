package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Cliente;

import java.util.ArrayList;
<<<<<<< Updated upstream
import java.util.Collections;
import java.util.List;
import java.util.Optional; // Para um retorno mais seguro ao buscar
=======
import java.util.List;
import java.util.Optional;
>>>>>>> Stashed changes

public class ClienteRepository {
    private final List<Cliente> clientes = new ArrayList<>();

<<<<<<< Updated upstream
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
=======
    public ClienteRepository() {
        // seed opcional
        clientes.add(new Cliente(1, "João Silva", "123.456.789-00", "joao@email.com"));
    }

    public List<Cliente> buscarTodos() { return new ArrayList<>(clientes); }

    public Optional<Cliente> buscarPorId(int id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst();
    }

    public void salvar(Cliente cliente) {
        // se id existir, atualiza; senão, adiciona
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == cliente.getId()) {
                clientes.set(i, cliente);
                return;
            }
        }
        clientes.add(cliente);
>>>>>>> Stashed changes
    }
}
