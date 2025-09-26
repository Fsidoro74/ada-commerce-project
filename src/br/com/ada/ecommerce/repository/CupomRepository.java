package br.com.ada.ecommerce.repository;

import br.com.ada.ecommerce.model.Cupom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CupomRepository {

    private final List<Cupom> cupons = new ArrayList<>();

    public void salvar(Cupom cupom) {
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
}