package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Venda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class VendaRepository {

    private final List<Venda> vendas = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1);

    /**
     * Salva uma nova venda. Se não tiver ID, gera automaticamente.
     */
    public void salvar(Venda venda) {
        if (venda == null) {
            throw new IllegalArgumentException("Venda não pode ser nula.");
        }

        if (venda.getId() == null) {
            venda.setId(sequence.getAndIncrement());
        }

        vendas.add(venda);
    }

    /**
     * Atualiza uma venda existente.
     */
    public void atualizar(Venda venda) {
        if (venda == null || venda.getId() == null) {
            throw new IllegalArgumentException("Venda inválida para atualização.");
        }

        for (int i = 0; i < vendas.size(); i++) {
            if (vendas.get(i).getId().equals(venda.getId())) {
                vendas.set(i, venda);
                return;
            }
        }

        throw new IllegalArgumentException("Venda não encontrada para atualização.");
    }

    /**
     * Busca uma venda pelo ID.
     */
    public Optional<Venda> buscarPorId(Long id) {
        if (id == null) return Optional.empty();

        return vendas.stream()
                .filter(venda -> id.equals(venda.getId()))
                .findFirst();
    }

    /**
     * Retorna todas as vendas registradas.
     */
    public List<Venda> buscarTodos() {
        return Collections.unmodifiableList(new ArrayList<>(vendas));
    }
}