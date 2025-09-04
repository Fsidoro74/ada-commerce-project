package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Pedido;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Collections;
import java.util.stream.Collectors;



public class PedidoRepository {
    private final List<Pedido> pedidos = new ArrayList<>();

    /**
     * Salva um novo pedido na lista.
     * @param pedido O pedido a ser salvo.
     */
    public void salvar(Pedido pedido) {
        // Em um sistema real, você verificaria se o pedido já existe por ID
        // Para este exercício em memória, simplesmente adicionamos.
        this.pedidos.add(pedido);
        System.out.println("Pedido #" + pedido.getId() + " salvo no repositório.");
    }

    /**
     * Lista todos os pedidos atualmente no repositório.
     * @return Uma lista imutável de todos os pedidos.
     */
    public List<Pedido> listarTodos() {
        return Collections.unmodifiableList(pedidos);
    }

    /**
     * Busca um pedido pelo seu ID.
     * @param id O ID do pedido a ser buscado.
     * @return Um Optional contendo o pedido, se encontrado; Optional.empty() caso contrário.
     */
    public Optional<Pedido> buscarPorId(long id) {
        return pedidos.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    /**
     * Busca todos os pedidos de um cliente específico.
     * @param clienteId O ID do cliente.
     * @return Uma lista imutável de pedidos do cliente.
     */
    public List<Pedido> buscarPorClienteId(long clienteId) {
        return pedidos.stream()
                .filter(p -> p.getCliente().getId() == clienteId)
                .collect(Collectors.toUnmodifiableList()); // <-- Correção aqui
    }
}