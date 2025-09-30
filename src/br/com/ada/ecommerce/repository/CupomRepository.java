package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Cupom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CupomRepository {

    private final List<Cupom> cupons = new ArrayList<>();
    private Long proximoId = 1L;

    public void salvar(Cupom cupom) {
        if (buscarPorCodigo(cupom.getCodigo()).isPresent()) {
            throw new IllegalArgumentException("Cupom com este código já existe.");
        }
        cupom.setId(proximoId++);
        cupons.add(cupom);
    }

    public List<Cupom> listarTodos() {
        return new ArrayList<>(cupons);
    }

    public Optional<Cupom> buscarPorCodigo(String codigo) {
        return cupons.stream()
                .filter(c -> c.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    public void atualizar(Cupom cupomAtualizado) {
        for (int i = 0; i < cupons.size(); i++) {
            if (cupons.get(i).getId().equals(cupomAtualizado.getId())) {
                cupons.set(i, cupomAtualizado);
                return;
            }
        }
        throw new IllegalArgumentException("Cupom não encontrado para atualização.");
    }
}