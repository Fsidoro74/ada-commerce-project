package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Venda;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class VendaRepository {
    private final List<Venda> vendas = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public void salvar(Venda venda) {
        if (venda.getId() == null) {
            venda.setId(sequence.getAndIncrement());
        }
        vendas.add(venda);
    }

    public void atualizar(Venda venda) {
        for (int i = 0; i < vendas.size(); i++) {
            if (vendas.get(i).getId().equals(venda.getId())) {
                vendas.set(i, venda);
                return;
            }
        }
        throw new IllegalArgumentException("Venda não encontrada para atualização");
    }

    public Optional<Venda> buscarPorId(Long id) {
        return vendas.stream()
                .filter(venda -> venda.getId().equals(id))
                .findFirst();
    }

    public List<Venda> buscarTodos() {
        return new ArrayList<>(vendas);
    }
}
