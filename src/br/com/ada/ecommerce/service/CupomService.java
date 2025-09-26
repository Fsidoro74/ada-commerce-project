package br.com.ada.ecommerce.service;

import br.com.ada.ecommerce.model.Cupom;
import br.com.ada.ecommerce.repository.CupomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CupomService {

    private final CupomRepository cupomRepository;

    public CupomService(CupomRepository cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    public void criarCupom(String codigo, double valorDesconto, boolean percentual, int diasValidade) {
        LocalDate validade = LocalDate.now().plusDays(diasValidade);
        Cupom cupom = new Cupom(null, codigo, valorDesconto, percentual, validade);
        cupomRepository.salvar(cupom);
    }

    public List<Cupom> listarCupons() {
        return cupomRepository.listarTodos();
    }

    public Optional<Cupom> buscarPorCodigo(String codigo) {
        return cupomRepository.buscarPorCodigo(codigo);
    }

    public boolean expirarCupom(String codigo) {
        return cupomRepository.buscarPorCodigo(codigo)
                .map(cupom -> {
                    cupom.marcarComoUtilizado();
                    return true;
                })
                .orElse(false);
    }

    public void expirarCuponsVencidos() {
        for (Cupom cupom : cupomRepository.listarTodos()) {
            if (LocalDate.now().isAfter(cupom.getValidade())) {
                cupom.marcarComoUtilizado();
            }
        }
    }

    public boolean atualizarCupom(String codigo, double novoValor, boolean novoTipo, int novaValidadeDias) {
        return cupomRepository.buscarPorCodigo(codigo)
                .map(cupom -> {
                    cupom.setValorDesconto(novoValor);
                    cupom.setPercentual(novoTipo);
                    cupom.setValidade(LocalDate.now().plusDays(novaValidadeDias));
                    return true;
                })
                .orElse(false);
    }
}